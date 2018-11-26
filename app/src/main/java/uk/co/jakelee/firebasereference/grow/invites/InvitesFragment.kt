package uk.co.jakelee.firebasereference.grow.invites


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.appinvite.AppInviteInvitation
import com.google.firebase.appinvite.FirebaseAppInvite
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import kotlinx.android.synthetic.main.fragment_develop_invites.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processPendingInvite()
        sendInviteButton.setOnClickListener { sendInviteOnClick() }
    }

    private fun sendInviteOnClick() = activity!!
            .startActivityForResult(
                    AppInviteInvitation.IntentBuilder("Share an invite!")
                            .setMessage("Here is the custom message inside the invitation!")
                            .setDeepLink(Uri.parse("https://firebasereference.jakelee.co.uk/deeplink"))
                            .setCustomImage(Uri.parse("https://via.placeholder.com/600x600/4286f4/000000?text=Invite+image!"))
                            .setCallToActionText("Accept invite")
                            .build(), Companion.INVITE_REQUEST_CODE)

    private fun processPendingInvite() = FirebaseDynamicLinks.getInstance()
            .getDynamicLink(activity!!.intent)
            .addOnSuccessListener { data ->
                if (data == null) {
                    Log.d("Invites", "getInvitation: no data")
                } else {
                    val deepLink = data.link
                    val inviteId = FirebaseAppInvite.getInvitation(data).invitationId
                    Toast.makeText(activity!!, "Retrieved deep link of $deepLink and an inviteId of $inviteId", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { Log.e("Invites", "Failed") }

    companion object {
        public const val INVITE_REQUEST_CODE = 54321
    }
}
