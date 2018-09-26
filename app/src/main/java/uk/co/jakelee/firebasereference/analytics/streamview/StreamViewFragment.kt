package uk.co.jakelee.firebasereference.analytics.streamview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class StreamViewFragment : BaseFirebaseFragment() {
    override val title = R.string.title_streamview
    override val tutorialUrl = R.string.tutorial_streamview
    override val docsUrl = R.string.documentation_streamview
    override val firebaseUrl = R.string.firebase_streamview

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics_streamview, container, false)
    }


}
