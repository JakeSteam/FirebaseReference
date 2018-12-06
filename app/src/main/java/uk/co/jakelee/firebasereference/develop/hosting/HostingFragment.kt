package uk.co.jakelee.firebasereference.develop.hosting


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_develop_hosting.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openSiteButton.setOnClickListener {
            startActivity(
                    Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.hosting_url)))
            )
        }
    }


}
