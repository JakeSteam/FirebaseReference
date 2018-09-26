package uk.co.jakelee.firebasereference.grow.in_app_messaging


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class InAppMessagingFragment : BaseFirebaseFragment() {
    override val title = R.string.title_in_app_messaging
    override val tutorialUrl = R.string.tutorial_in_app_messaging
    override val docsUrl = R.string.documentation_in_app_messaging
    override val firebaseUrl = R.string.firebase_in_app_messaging

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grow_in_app_messaging, container, false)
    }


}
