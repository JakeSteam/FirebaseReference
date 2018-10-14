package uk.co.jakelee.firebasereference.develop.cloud_firestore


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_develop_cloud_firestore.*
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R
import java.text.SimpleDateFormat
import java.util.*

class CloudFirestoreFragment : BaseFirebaseFragment() {
    override val title = R.string.title_cloud_firestore
    override val tutorialUrl = R.string.tutorial_cloud_firestore
    override val docsUrl = R.string.documentation_cloud_firestore
    override val firebaseUrl = R.string.firebase_cloud_firestore

    private val db = FirebaseFirestore.getInstance()
    private val tableName = "exampleTable"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_develop_cloud_firestore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnclicks()
        db.collection(tableName).addSnapshotListener { querySnapshot, _ ->
            displayDocuments(querySnapshot!!.documents)
        }
    }


    private fun setOnclicks() {
        fetchButton.setOnClickListener { fetchRows() }
        addAutomaticButton.setOnClickListener { addImplicitDocument() }
        addManualButton.setOnClickListener { addExplicitDocument() }
        editButton.setOnClickListener { editAllRows() }
        deleteButton.setOnClickListener { deleteRow() }
    }

    private fun fetchRows() = db.collection(tableName)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        displayDocuments(task.result?.documents!!)
                    } else {
                        showToast("Error getting documents: ${task.exception}")
                        tableContents.text = ""
                    }
                }

    private fun addImplicitDocument() = db.collection(tableName)
                .add(generateDocument())
                .addOnSuccessListener { documentReference ->
                    showToast("DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    showToast("Error adding document: $e")
                }

    private fun addExplicitDocument() {
        val id = (1..100000).shuffled().first().toString()
        db.collection(tableName).document(id)
                .set(generateDocument())
                .addOnSuccessListener {
                    showToast("DocumentSnapshot added with ID: $id")
                }
                .addOnFailureListener { e ->
                    showToast("Error writing document: $e")
                }
    }

    private fun editAllRows() = db.collection(tableName)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result!!.documents.forEach {
                            val ref = db.collection(tableName).document(it.id)
                            it.data?.let {
                                ref.update("number", (it["number"] as Long) + 1)
                            }
                        }
                    } else {
                        showToast("Error getting documents: ${task.exception}")
                        tableContents.text = ""
                    }
                }

    private fun deleteRow() = db.collection(tableName)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result!!.documents[0]?.let {
                            val ref = db.collection(tableName).document(it.id)
                            ref.delete()
                            showToast("DocumentSnapshot successfully deleted with ID: ${ref.id}")
                        }
                    } else {
                        showToast("Error getting documents: ${task.exception}")
                        tableContents.text = ""
                    }
                }

    private fun generateDocument(): HashMap<String, Any> {
        val dateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        return hashMapOf(
                "number" to (1..100).shuffled().first(),
                "added" to dateFormat.format(Calendar.getInstance().time))
    }

    private fun displayDocuments(t: List<DocumentSnapshot>) {
        var text = ""
        for (document in t) {
            text += "ID ${document.id} has number ${document.data!!["number"]}\n"
        }
        tableContents.text = text
    }

    private fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }
}
