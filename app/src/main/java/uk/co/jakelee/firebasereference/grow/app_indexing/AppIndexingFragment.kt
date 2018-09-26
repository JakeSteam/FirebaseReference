package uk.co.jakelee.firebasereference.grow.app_indexing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class AppIndexingFragment : BaseFirebaseFragment() {
    override val title = R.string.title_app_indexing
    override val tutorialUrl = R.string.tutorial_app_indexing
    override val docsUrl = R.string.documentation_app_indexing
    override val firebaseUrl = R.string.firebase_app_indexing

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grow_app_indexing, container, false)
    }


}
