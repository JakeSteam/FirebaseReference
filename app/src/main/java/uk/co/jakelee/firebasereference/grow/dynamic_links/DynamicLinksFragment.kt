package uk.co.jakelee.firebasereference.grow.dynamic_links


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class DynamicLinksFragment : BaseFirebaseFragment() {
    override val title = R.string.title_dynamic_links
    override val tutorialUrl = R.string.tutorial_dynamic_links
    override val docsUrl = R.string.documentation_dynamic_links
    override val firebaseUrl = R.string.firebase_dynamic_links

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grow_dynamic_links, container, false)
    }


}
