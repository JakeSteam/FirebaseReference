package uk.co.jakelee.firebasereference.analytics.events


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class EventsFragment : BaseFirebaseFragment() {
    override val title = R.string.app_name
    override val tutorialUrl = 0
    override val docsUrl = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics_events, container, false)
    }


}
