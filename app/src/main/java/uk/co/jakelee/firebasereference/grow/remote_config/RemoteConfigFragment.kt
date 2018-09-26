package uk.co.jakelee.firebasereference.grow.remote_config


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class RemoteConfigFragment : BaseFirebaseFragment() {
    override val title = R.string.title_remote_config
    override val tutorialUrl = R.string.tutorial_remote_config
    override val docsUrl = R.string.documentation_remote_config
    override val firebaseUrl = R.string.firebase_remote_config

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grow_remote_config, container, false)
    }


}
