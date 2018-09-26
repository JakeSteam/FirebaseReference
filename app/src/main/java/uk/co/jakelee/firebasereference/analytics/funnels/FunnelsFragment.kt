package uk.co.jakelee.firebasereference.analytics.funnels


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class FunnelsFragment : BaseFirebaseFragment() {
    override val title = R.string.title_funnels
    override val tutorialUrl = R.string.tutorial_funnels
    override val docsUrl = R.string.documentation_funnels
    override val firebaseUrl = R.string.firebase_funnels
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics_funnels, container, false)
    }


}
