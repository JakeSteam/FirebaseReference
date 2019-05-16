package uk.co.jakelee.firebasereference.grow.dynamic_links


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import kotlinx.android.synthetic.main.fragment_grow_dynamic_links.*


class DynamicLinksFragment : BaseFirebaseFragment() {
    override val title = R.string.title_dynamic_links
    override val tutorialUrl = R.string.tutorial_dynamic_links
    override val docsUrl = R.string.documentation_dynamic_links
    override val firebaseUrl = R.string.firebase_dynamic_links

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        makeSimpleLink.setOnClickListener { buildSimpleLink() }
        makeShortLink.setOnClickListener { buildShortLink() }
        makeVeryShortLink.setOnClickListener { buildVeryShortLink() }
        return inflater.inflate(R.layout.fragment_grow_dynamic_links, container, false)
    }

    private val linkBuilder = FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(Uri.parse("https://www.example.com/"))
            .setDomainUriPrefix("https://example.page.link")
            .setAndroidParameters(DynamicLink.AndroidParameters.Builder().build())

    private fun buildSimpleLink() {
        val dynamicLink = linkBuilder.buildDynamicLink()
        copyToClipboard(dynamicLink.uri.toString())
    }

    private fun buildShortLink() = linkBuilder
            .buildShortDynamicLink()
            .addOnSuccessListener {
                copyToClipboard(it.shortLink.toString())
            }
            .addOnFailureListener {
                showToast(it.localizedMessage)
            }

    private fun buildVeryShortLink() = linkBuilder
            .buildShortDynamicLink()
            .addOnSuccessListener {
                copyToClipboard(it.shortLink.toString())
            }
            .addOnFailureListener {
                showToast(it.localizedMessage)
            }
}
