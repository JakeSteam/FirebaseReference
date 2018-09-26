package uk.co.jakelee.firebasereference.analytics.latest_release


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class LatestReleaseFragment : BaseFirebaseFragment() {
    override val title = R.string.title_latest_release
    override val tutorialUrl = R.string.tutorial_latest_release
    override val docsUrl = R.string.documentation_latest_release
    override val firebaseUrl = R.string.firebase_latest_release

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics_latest_release, container, false)
    }


}
