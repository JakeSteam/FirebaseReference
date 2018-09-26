package uk.co.jakelee.firebasereference.develop.functions


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class FunctionsFragment : BaseFirebaseFragment() {
    override val title = R.string.title_functions
    override val tutorialUrl = R.string.tutorial_functions
    override val docsUrl = R.string.documentation_functions
    override val firebaseUrl = R.string.firebase_functions

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_develop_functions, container, false)
    }


}
