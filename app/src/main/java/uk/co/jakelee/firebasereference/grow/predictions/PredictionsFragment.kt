package uk.co.jakelee.firebasereference.grow.predictions


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class PredictionsFragment : BaseFirebaseFragment() {
    override val title = R.string.title_predictions
    override val tutorialUrl = R.string.tutorial_predictions
    override val docsUrl = R.string.documentation_predictions
    override val firebaseUrl = R.string.firebase_predictions

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grow_predictions, container, false)
    }


}
