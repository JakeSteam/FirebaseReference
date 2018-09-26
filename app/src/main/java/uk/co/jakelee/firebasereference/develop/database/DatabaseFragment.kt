package uk.co.jakelee.firebasereference.develop.database


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class DatabaseFragment : BaseFirebaseFragment() {
    override val title = R.string.title_database
    override val tutorialUrl = R.string.tutorial_database
    override val docsUrl = R.string.documentation_database
    override val firebaseUrl = R.string.firebase_database

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_develop_database, container, false)
    }


}
