package uk.co.jakelee.firebasereference.develop.invites


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class InvitesFragment : BaseFirebaseFragment() {
    override val title = R.string.title_invites
    override val tutorialUrl = R.string.tutorial_invites
    override val docsUrl = R.string.documentation_invites
    override val firebaseUrl = R.string.firebase_invites

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_develop_invites, container, false)
    }


}
