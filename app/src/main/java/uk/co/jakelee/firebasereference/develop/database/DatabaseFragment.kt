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
        addButton.setOnClickListener { addRow() }
        editAllButton.setOnClickListener { editAllRows() }
        deleteAllButton.setOnClickListener { deleteAllRows() }
        analyseButton.setOnClickListener { analyseRows() }
    }

    private fun addRow() {
        val key = nestedData.push().key
        key?.let {
            nestedData.child(key).setValue(DataRow(
                    uuid = java.util.UUID.randomUUID().toString(),
                    number = (1..1000).shuffled().first().toString()))
        }
    }

    private fun editAllRows() = nestedData.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (postSnapshot in dataSnapshot.children) {
                val child = nestedData.child(postSnapshot.key.toString()).child("uuid")
                val existingUuid = postSnapshot.child("uuid").value
                child.setValue("*$existingUuid")
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            showToast(databaseError.toException().toString())
        }
    })

    private fun deleteAllRows() = nestedData.setValue(null)

    private fun displayData(data: String) {
        tableContents.text = data
    }

    private fun analyseRows() = nestedData.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val numItems = dataSnapshot.childrenCount
                if (numItems > 0) {
                    var minValue = Int.MAX_VALUE
                    var maxValue = Int.MIN_VALUE
                    dataSnapshot.children.forEach {
                        val number = it.child("number").value.toString().toIntOrNull()
                        number?.let {
                            if (number > maxValue) {
                                maxValue = number
                            } else if (number < minValue) {
                                minValue = number
                            }
                        }
                    }
                    showToast("Database has $numItems item(s), with a minimum of $minValue and a maximum of $maxValue")
                } else {
                    showToast("No items to analyse!")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                showToast(databaseError.toException().toString())
            }
        })
}

data class DataRow(val uuid: String, val number: String)
