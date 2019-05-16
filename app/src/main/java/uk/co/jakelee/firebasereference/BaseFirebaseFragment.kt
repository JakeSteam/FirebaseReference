package uk.co.jakelee.firebasereference

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast

abstract class BaseFirebaseFragment : Fragment() {
    abstract val title: Int
    abstract val tutorialUrl: Int
    abstract val docsUrl: Int
    abstract val firebaseUrl: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (title > 0) {
            activity!!.title = getString(title)
        } else {
            activity!!.title = getString(R.string.app_name)
        }
    }

    fun openTutorial() {
        if (tutorialUrl > 0 && getString(tutorialUrl).isNotEmpty()) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(tutorialUrl))))
        } else {
            Toast.makeText(activity!!, "Couldn't find a tutorial URL to open!", Toast.LENGTH_SHORT).show()
        }
    }

    fun openDocs() {
        if (docsUrl > 0 && getString(docsUrl).isNotEmpty()) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(docsUrl))))
        } else {
            Toast.makeText(activity!!, "Couldn't find a documentation URL to open!", Toast.LENGTH_SHORT).show()
        }
    }

    fun openFirebase() {
        if (firebaseUrl > 0 && getString(firebaseUrl).isNotEmpty()) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(firebaseUrl))))
        } else {
            Toast.makeText(activity!!, "Couldn't find a firebase URL to open!", Toast.LENGTH_SHORT).show()
        }
    }

    fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

    fun copyToClipboard(text: String) {
        val clipboard = context!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied content", text)
        clipboard.primaryClip = clip
        showToast("$text copied to clipboard!")
    }
}