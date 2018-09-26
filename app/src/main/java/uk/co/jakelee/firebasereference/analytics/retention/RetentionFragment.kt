package uk.co.jakelee.firebasereference.analytics.retention


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class RetentionFragment : BaseFirebaseFragment() {
    override val title = R.string.title_retention
    override val tutorialUrl = R.string.tutorial_retention
    override val docsUrl = R.string.documentation_retention
    override val firebaseUrl = R.string.firebase_retention

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics_retention, container, false)
    }


}
