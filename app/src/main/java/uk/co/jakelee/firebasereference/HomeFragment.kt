package uk.co.jakelee.firebasereference


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class HomeFragment : BaseFirebaseFragment() {
    override val title = R.string.app_name
    override val tutorialUrl = 0
    override val docsUrl = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}
