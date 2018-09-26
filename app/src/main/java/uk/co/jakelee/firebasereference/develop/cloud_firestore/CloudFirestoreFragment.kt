package uk.co.jakelee.firebasereference.develop.cloud_firestore


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class CloudFirestoreFragment : BaseFirebaseFragment() {
    override val title = R.string.title_cloud_firestore
    override val tutorialUrl = R.string.tutorial_cloud_firestore
    override val docsUrl = R.string.documentation_cloud_firestore
    override val firebaseUrl = R.string.firebase_cloud_firestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_develop_cloud_firestore, container, false)
    }


}
