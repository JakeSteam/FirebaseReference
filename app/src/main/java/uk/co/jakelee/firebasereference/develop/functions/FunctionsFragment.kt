package uk.co.jakelee.firebasereference.develop.functions


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R
import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions
import kotlinx.android.synthetic.main.fragment_develop_functions.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnclicks()
    }

    private fun setOnclicks() {
        getRowCount.setOnClickListener { getRowCount() }
        uppercaseManufacturer.setOnClickListener { uppercaseManufacturer() }
    }

    private fun getRowCount() =
        FirebaseFunctions.getInstance()
                .getHttpsCallable("getRowCount")
                .call()
                .continueWith { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(activity, "There are currently ${task.result!!.data} rows in Cloud Firestore", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

    private fun uppercaseManufacturer() =
        FirebaseFunctions.getInstance()
                .getHttpsCallable("uppercaseDeviceName")
                .call(hashMapOf("manufacturer" to Build.MANUFACTURER))
                .continueWith { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(activity, "Manufacturer is ${task.result!!.data}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
}
