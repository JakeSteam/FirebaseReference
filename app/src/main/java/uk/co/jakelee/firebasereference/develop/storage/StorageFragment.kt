package uk.co.jakelee.firebasereference.develop.storage


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


}
