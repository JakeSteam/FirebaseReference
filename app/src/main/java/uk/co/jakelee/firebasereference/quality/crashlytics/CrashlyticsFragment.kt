package uk.co.jakelee.firebasereference.quality.crashlytics


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.crashlytics.android.Crashlytics
import kotlinx.android.synthetic.main.fragment_quality_crashlytics.*
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class CrashlyticsFragment : BaseFirebaseFragment() {
    override val title = R.string.title_crashlytics
    override val tutorialUrl = R.string.tutorial_crashlytics
    override val docsUrl = R.string.documentation_crashlytics
    override val firebaseUrl = R.string.firebase_crashlytics

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quality_crashlytics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Crashlytics.setUserIdentifier(Build.FINGERPRINT)
        forceCrash.setOnClickListener {
            Crashlytics.getInstance().crash()
        }
        createLog.setOnClickListener {
            Crashlytics.log("Create log was clicked!")
            Toast.makeText(activity!!, "Crashlytics log was created!", Toast.LENGTH_SHORT).show()
        }
        updateKey.setOnClickListener {
            Crashlytics.setInt("randomNumber", (1..1000).shuffled().last())
        }
    }
}
