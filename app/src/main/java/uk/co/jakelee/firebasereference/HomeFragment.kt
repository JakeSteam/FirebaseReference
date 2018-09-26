package uk.co.jakelee.firebasereference


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class HomeFragment : BaseFirebaseFragment() {
    override val title = R.string.app_name
    override val tutorialUrl = R.string.tutorial_home
    override val docsUrl = R.string.documentation_home
    override val firebaseUrl = R.string.firebase_home

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}
