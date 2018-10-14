package uk.co.jakelee.firebasereference.develop.cloud_firestore


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_develop_cloud_firestore.*
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R
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
        updateRowList()
    }

    private fun setOnclicks() {
        updateButton.setOnClickListener { updateRowList() }
        addAutomaticButton.setOnClickListener { addImplicitDocument() }
        addManualButton.setOnClickListener { addExplicitDocument() }
        editButton.setOnClickListener { updateRow() }
        deleteButton.setOnClickListener { deleteRow() }
    }

    private fun updateRowList() {
        db.collection(tableName)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var text = "ID\tNumber\tAdded"
                        for (document in task.result!!) {
                            text += "${document.id}\t${document.data["number"]}\t${document.data["added"]}"
                        }
                        tableContents.text = text
                    } else {
                        showToast("Error getting documents: ${task.exception}")
                        tableContents.text = ""
                    }
                }
    }

    private fun addImplicitDocument() {
        db.collection(tableName)
                .add(generateDocument())
                .addOnSuccessListener { documentReference ->
                    showToast("DocumentSnapshot added with ID: ${documentReference.id}")
                    updateRowList()
                }
                .addOnFailureListener { e ->
                    showToast("Error adding document: $e")
                }
    }

    private var rowId = 0
    private fun addExplicitDocument() {
        db.collection(tableName).document(rowId.toString())
                .set(generateDocument())
                .addOnSuccessListener {
                    showToast("DocumentSnapshot added with ID: $rowId")
                    updateRowList()
                }
                .addOnFailureListener { e ->
                    showToast("Error writing document: $e")
                }
        rowId++
    }

    private fun updateRow() {
        // get row
        // change row (add 1 to number, change updated)
    }

    private fun deleteRow() {
        db.collection(tableName).document(0.toString())
                .delete()
                .addOnSuccessListener {
                    showToast("DocumentSnapshot successfully deleted with ID: 0")
                    updateRowList()
                }
                .addOnFailureListener { e ->
                    showToast("Error deleting document with ID 0: $e")
                }
    }

    private fun generateDocument(): HashMap<String, Any> {
        return hashMapOf(
                "number" to (1..100).shuffled().first(),
                "added" to Calendar.getInstance().time.toString())
    }

    private fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }
}
