package uk.co.jakelee.firebasereference.analytics.user_properties


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class UserPropertiesFragment : BaseFirebaseFragment() {
    override val title = R.string.title_user_properties
    override val tutorialUrl = R.string.tutorial_user_properties
    override val docsUrl = R.string.documentation_user_properties
    override val firebaseUrl = R.string.firebase_user_properties

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics_user_properties, container, false)
    }


}
