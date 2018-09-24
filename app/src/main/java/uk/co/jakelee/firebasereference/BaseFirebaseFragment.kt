package uk.co.jakelee.firebasereference

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFirebaseFragment : Fragment() {
    abstract val title: Int
    abstract val tutorialUrl: Int
    abstract val docsUrl: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (title > 0) {
            activity!!.title = getString(title)
        } else {
            activity!!.title = getString(R.string.app_name)
        }
    }
}