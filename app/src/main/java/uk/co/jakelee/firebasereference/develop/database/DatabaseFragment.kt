package uk.co.jakelee.firebasereference.develop.database


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_develop_database.*
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R


class DatabaseFragment : BaseFirebaseFragment() {
    override val title = R.string.title_database
    override val tutorialUrl = R.string.tutorial_database
    override val docsUrl = R.string.documentation_database
    override val firebaseUrl = R.string.firebase_database

    private val db = FirebaseDatabase.getInstance()
    private val nestedData = db.getReference("nestedData")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_develop_database, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnclicks()
        nestedData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                displayData(dataSnapshot.value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                showToast("Failed to read value: ${error.toException()}")
            }
        })
    }


    private fun setOnclicks() {
        addButton.setOnClickListener { addDocument() }
        deleteAllButton.setOnClickListener { deleteAllRows() }
    }

    private fun addDocument() = nestedData.child(java.util.UUID.randomUUID().toString())
            .setValue((1..1000).shuffled().first())

    private fun deleteAllRows() = nestedData.setValue("")

    private fun displayData(data: String) {
        tableContents.text = data
    }
}
