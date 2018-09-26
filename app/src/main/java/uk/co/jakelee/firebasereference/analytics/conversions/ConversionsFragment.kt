package uk.co.jakelee.firebasereference.analytics.conversions


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class ConversionsFragment : BaseFirebaseFragment() {
    override val title = R.string.title_conversions
    override val tutorialUrl = R.string.tutorial_conversions
    override val docsUrl = R.string.documentation_conversions
    override val firebaseUrl = R.string.firebase_conversions

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics_conversions, container, false)
    }


}
