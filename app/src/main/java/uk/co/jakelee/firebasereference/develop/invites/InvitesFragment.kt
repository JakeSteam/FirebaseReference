package uk.co.jakelee.firebasereference.develop.invites


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.appinvite.AppInviteInvitation
import kotlinx.android.synthetic.main.fragment_develop_invites.*
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class InvitesFragment : BaseFirebaseFragment() {
    override val title = R.string.title_invites
    override val tutorialUrl = R.string.tutorial_invites
    override val docsUrl = R.string.documentation_invites
    override val firebaseUrl = R.string.firebase_invites
    private val INVITE_REQUEST_CODE = 54321

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_develop_invites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendInviteButton.setOnClickListener {
            activity!!.startActivityForResult(
                    AppInviteInvitation.IntentBuilder("title")
                        .setMessage("message")
                        .setDeepLink(Uri.parse("deep link"))
                        .setCustomImage(Uri.parse("custom image url"))
                        .setCallToActionText("call to action")
                        .build(), INVITE_REQUEST_CODE)
        }
    }


}
