package uk.co.jakelee.firebasereference.develop.storage


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.element_storage_row_sample.view.*
import kotlinx.android.synthetic.main.fragment_develop_storage.*
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R


class StorageFragment : BaseFirebaseFragment() {
    override val title = R.string.title_storage
    override val tutorialUrl = R.string.tutorial_storage
    override val docsUrl = R.string.documentation_storage
    override val firebaseUrl = R.string.firebase_storage

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_develop_storage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val storage = FirebaseStorage.getInstance()

        val sampleDirectory = storage.reference.child("sample")
        val sampleFiles = arrayOf("Cat.jpg", "Dog.jpg", "Duck.jpg")
        updateTable(sampleFilesTable, sampleDirectory, sampleFiles, false)

        //val userDirectory = storage.reference.child("userFolder").child(Build.FINGERPRINT)
        // load user files from something like a shared pref
        // updateTable(userFilesTable, userDirectory, userFiles, true)
    }

    private fun updateTable(table: TableLayout, sampleDirectory: StorageReference, files: Array<String>, canDelete: Boolean) {
        val inflater = LayoutInflater.from(activity!!)
        for (file in files) {
            val reference = sampleDirectory.child(file)
            val row = inflater.inflate(R.layout.element_storage_row_sample, table, false)
            row.filename.text = reference.name
            row.viewMetadataButton.setOnClickListener { viewMetadata(reference) }
            row.downloadButton.setOnClickListener { downloadFile(reference) }
            table.addView(row)
        }
    }

    private fun viewMetadata(reference: StorageReference) = reference.metadata
            .addOnSuccessListener {
                Toast.makeText(activity, "${it.name} has a size of ${it.sizeBytes} bytes, and is a ${it.contentType}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(activity, "Failed to load metadata: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }

    private fun downloadFile(reference: StorageReference) = reference.getBytes(10 * 1024 * 1024)
            .addOnSuccessListener { bytes ->
                activity!!.openFileOutput(reference.name, Context.MODE_PRIVATE).use {
                    it.write(bytes)
                    Toast.makeText(activity, "Downloaded ${reference.name} from ${it}!", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(activity, "Failed to download file: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }

    private fun uploadFile() {
        // Display file picker
        // Upload bytes
        // Store reference somewhere, so that the user folder can display it
    }
}
