package uk.co.jakelee.firebasereference.analytics.audiences


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class AudiencesFragment : BaseFirebaseFragment() {
    override val title = R.string.title_audiences
    override val tutorialUrl = R.string.tutorial_audiences
    override val docsUrl = R.string.documentation_audiences
    override val firebaseUrl = R.string.firebase_audiences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics_audiences, container, false)
    }


}
