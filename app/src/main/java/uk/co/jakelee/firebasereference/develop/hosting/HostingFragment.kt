package uk.co.jakelee.firebasereference.develop.hosting


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class HostingFragment : BaseFirebaseFragment() {
    override val title = R.string.title_hosting
    override val tutorialUrl = R.string.tutorial_hosting
    override val docsUrl = R.string.documentation_hosting
    override val firebaseUrl = R.string.firebase_hosting

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_develop_hosting, container, false)
    }


}
