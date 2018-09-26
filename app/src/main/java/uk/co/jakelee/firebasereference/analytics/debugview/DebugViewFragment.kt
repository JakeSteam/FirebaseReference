package uk.co.jakelee.firebasereference.analytics.debugview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class DebugViewFragment : BaseFirebaseFragment() {
    override val title = R.string.title_debugview
    override val tutorialUrl = R.string.tutorial_debugview
    override val docsUrl = R.string.documentation_debugview
    override val firebaseUrl = R.string.firebase_debugview

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics_debugview, container, false)
    }


}
