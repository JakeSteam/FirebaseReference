package uk.co.jakelee.firebasereference.develop.storage


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
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
import java.io.File


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

        updateSampleTable(storage)
        updateUserTable(storage)

        uploadButton.setOnClickListener {
            startActivityForResult(
                    Intent(Intent.ACTION_GET_CONTENT).setType("*/*"), 1234)
        }
    }

    private fun updateUserTable(storage: FirebaseStorage) {
        val userDirectory = getUserDirectory(storage)
        val userFiles = getUserFiles()
        updateTable(userFilesTable, userDirectory, userFiles, true)
    }

    private fun updateSampleTable(storage: FirebaseStorage) {
        val sampleDirectory = storage.reference.child("sample")
        val sampleFiles = setOf("Cat.jpg", "Dog.jpg", "Duck.jpg")
        updateTable(sampleFilesTable, sampleDirectory, sampleFiles, false)
    }

    private fun updateTable(table: TableLayout, sampleDirectory: StorageReference, files: Set<String>, canDelete: Boolean) {
        val inflater = LayoutInflater.from(activity!!)
        table.removeAllViews()
        for (file in files) {
            val reference = sampleDirectory.child(file)
            val row = inflater.inflate(R.layout.element_storage_row_sample, table, false)
            row.filename.text = reference.name
            row.viewMetadataButton.setOnClickListener { viewMetadata(reference) }
            row.downloadButton.setOnClickListener { downloadFile(reference) }
            if (canDelete) {
                row.deleteButton.visibility = View.VISIBLE
                row.deleteButton.setOnClickListener { deleteFile(reference) }
            }
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

    private fun downloadFile(reference: StorageReference) {
        val file = File.createTempFile("download_", reference.name)
        reference.getFile(file).addOnSuccessListener {
            Toast.makeText(activity, "Saved  ${reference.name} (${it.totalByteCount}bytes) to ${file.absolutePath}!", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(activity, "Failed to download file: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val file = Uri.fromFile(File(data!!.dataString))
        val userDirectory = getUserDirectory(FirebaseStorage.getInstance()).child("1234")
        userDirectory.putFile(Uri.parse(data.dataString))
                .addOnSuccessListener {
                    Toast.makeText(activity, "Successfully uploaded ${file.lastPathSegment}!", Toast.LENGTH_SHORT).show()
                    addUserFile(file.lastPathSegment)
                    updateUserTable(FirebaseStorage.getInstance())
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Failed to upload ${file.lastPathSegment}!", Toast.LENGTH_SHORT).show()
                }
        super.onActivityResult(requestCode, resultCode, data)

    }

    private fun deleteFile(reference: StorageReference) = reference
            .delete()
            .addOnSuccessListener {
                Toast.makeText(activity, "Deleted  ${reference.name}!", Toast.LENGTH_SHORT).show()
                deleteUserFile(reference.name)
                updateUserTable(FirebaseStorage.getInstance())
            }.addOnFailureListener {
                Toast.makeText(activity, "Failed to delete file: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }



    private fun getUserDirectory(storage: FirebaseStorage) =
            storage.reference.child("userFolder").child("${Build.BRAND}/${Build.PRODUCT}/${Build.MODEL}")

    private fun getUserFiles() =
            PreferenceManager.getDefaultSharedPreferences(activity!!).getStringSet("uploaded_files", setOf())

    private fun addUserFile(file: String) {
        val files = getUserFiles().toMutableSet()
        files.add(file)
        PreferenceManager.getDefaultSharedPreferences(activity!!).edit()
                .putStringSet("uploaded_files", files).apply()
    }

    private fun deleteUserFile(file: String) {
        val files = getUserFiles()
        files.remove(file)
        PreferenceManager.getDefaultSharedPreferences(activity!!).edit()
                .putStringSet("uploaded_files", files).apply()
    }
}
