package uk.co.jakelee.firebasereference.grow.admob


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class AdmobFragment : BaseFirebaseFragment() {
    override val title = R.string.title_admob
    override val tutorialUrl = R.string.tutorial_admob
    override val docsUrl = R.string.documentation_admob
    override val firebaseUrl = R.string.firebase_admob

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grow_admob, container, false)
    }


}
