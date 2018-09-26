package uk.co.jakelee.firebasereference.develop.authentication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class AuthenticationFragment : BaseFirebaseFragment() {
    override val title = R.string.title_authentication
    override val tutorialUrl = R.string.tutorial_authentication
    override val docsUrl = R.string.documentation_authentication
    override val firebaseUrl = R.string.firebase_authentication

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_develop_authentication, container, false)
    }


}
