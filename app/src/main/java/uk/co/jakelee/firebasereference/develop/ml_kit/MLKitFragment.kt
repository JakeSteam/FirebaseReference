package uk.co.jakelee.firebasereference.develop.ml_kit


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class MLKitFragment : BaseFirebaseFragment() {
    override val title = R.string.title_ml_kit
    override val tutorialUrl = R.string.tutorial_ml_kit
    override val docsUrl = R.string.documentation_ml_kit
    override val firebaseUrl = R.string.firebase_ml_kit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_develop_ml_kit, container, false)
    }


}
