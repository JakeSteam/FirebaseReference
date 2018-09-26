package uk.co.jakelee.firebasereference.analytics.dashboard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class DashboardFragment : BaseFirebaseFragment() {
    override val title = R.string.title_dashboard
    override val tutorialUrl = R.string.tutorial_dashboard
    override val docsUrl = R.string.documentation_dashboard
    override val firebaseUrl = R.string.firebase_dashboard

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics_dashboard, container, false)
    }


}
