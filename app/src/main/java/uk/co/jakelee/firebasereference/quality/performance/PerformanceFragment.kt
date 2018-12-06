package uk.co.jakelee.firebasereference.quality.performance


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.metrics.AddTrace
import kotlinx.android.synthetic.main.fragment_quality_performance.*
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R
import java.lang.Thread.sleep

class PerformanceFragment : BaseFirebaseFragment() {
    override val title = R.string.title_performance
    override val tutorialUrl = R.string.tutorial_performance
    override val docsUrl = R.string.documentation_performance
    override val firebaseUrl = R.string.firebase_performance

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quality_performance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manualTrace.setOnClickListener { performManualTrace() }
        automaticTrace.setOnClickListener { performAutomaticTrace() }
    }

    private fun performManualTrace() {
        val trace = FirebasePerformance.getInstance().newTrace("manual")
        trace.start()
        trace.putAttribute("run_manual", true.toString())
        for (i in 1..(1..10).shuffled().last()) {
            trace.incrementMetric("manual_counter", 1)
            sleep(100)
        }
        trace.stop()
        Toast.makeText(activity!!, getString(R.string.performance_manual), Toast.LENGTH_SHORT).show()
    }

    @AddTrace(name = "automatic")
    private fun performAutomaticTrace() {
        for (i in 1..(1..20).shuffled().last()) {
            Log.d("Performance", "Value is $i")
            sleep(100)
        }
        Toast.makeText(activity!!, getString(R.string.performance_automatic), Toast.LENGTH_SHORT).show()
    }
}
