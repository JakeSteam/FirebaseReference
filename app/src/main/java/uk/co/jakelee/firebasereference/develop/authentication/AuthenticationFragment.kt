package uk.co.jakelee.firebasereference.develop.authentication


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserInfo
import kotlinx.android.synthetic.main.fragment_develop_authentication.*
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R
import java.text.DateFormat
import java.util.*


class AuthenticationFragment : BaseFirebaseFragment() {
    override val title = R.string.title_authentication
    override val tutorialUrl = R.string.tutorial_authentication
    override val docsUrl = R.string.documentation_authentication
    override val firebaseUrl = R.string.firebase_authentication

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_develop_authentication, container, false)
    }

    private val REQUEST_CODE = 10101

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener { clickLogin() }
        customLoginButton.setOnClickListener { clickCustomLogin() }
        logoutButton.setOnClickListener { clickLogout() }
    }

    private fun clickLogin() = startActivityForResult(
            AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(listOf(
                            AuthUI.IdpConfig.EmailBuilder().build(),
                            AuthUI.IdpConfig.GoogleBuilder().build()))
                    .build(), REQUEST_CODE)

    private fun clickCustomLogin() = startActivityForResult(
            AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(listOf(
                            AuthUI.IdpConfig.EmailBuilder().build(),
                            AuthUI.IdpConfig.GoogleBuilder().build()))
                    .setLogo(R.drawable.ic_firebase)
                    .setTheme(R.style.AuthenticationCustomTheme)
                    .setTosAndPrivacyPolicyUrls(
                            "https://google.com",
                            "https://firebase.google.com")
                    .build(), REQUEST_CODE)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != REQUEST_CODE) return
        if (resultCode == RESULT_OK) {
            loginButton.isEnabled = false
            customLoginButton.isEnabled = false
            logoutButton.isEnabled = true
            FirebaseAuth.getInstance().currentUser?.let {
                var providerData = ""
                for (provider in it.providerData) {
                    providerData += extractProviderData(provider)
                }
                providerData += extractUserDates(it)
                loggedInData.text = providerData
                Toast.makeText(activity, getString(R.string.authentication_logged_in), Toast.LENGTH_SHORT).show()
            }
        } else {
            IdpResponse.fromResultIntent(data)?.let {
                val errorCode = it.error?.errorCode
                val errorMessage = it.error?.localizedMessage
                Toast.makeText(activity, String.format(getString(R.string.authentication_login_error), errorCode, errorMessage), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun extractProviderData(userInfo: UserInfo) =
            String.format(getString(R.string.authentication_logged_in_provider),
                    userInfo.providerId,
                    userInfo.uid,
                    userInfo.displayName,
                    userInfo.email,
                    userInfo.photoUrl)

    private fun extractUserDates(user: FirebaseUser) =
            String.format(getString(R.string.authentication_logged_in_dates),
                    user.metadata?.creationTimestamp?.toTimeString(),
                    user.metadata?.lastSignInTimestamp?.toTimeString())

    private fun Long.toTimeString() =
            DateFormat.getDateTimeInstance().format(Date(this))


    private fun clickLogout() =
            AuthUI.getInstance()
                    .signOut(activity!!)
                    .addOnCompleteListener {
                        loginButton.isEnabled = true
                        customLoginButton.isEnabled = true
                        logoutButton.isEnabled = false
                        loggedInData.text = ""
                        Toast.makeText(activity!!, getString(R.string.authentication_logged_out), Toast.LENGTH_SHORT).show()
                    }
}
