package uk.co.jakelee.firebasereference.quality.test_lab


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class TestLabFragment : BaseFirebaseFragment() {
    override val title = R.string.title_test_lab
    override val tutorialUrl = R.string.tutorial_test_lab
    override val docsUrl = R.string.documentation_test_lab
    override val firebaseUrl = R.string.firebase_test_lab

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quality_test_lab, container, false)
    }


}
