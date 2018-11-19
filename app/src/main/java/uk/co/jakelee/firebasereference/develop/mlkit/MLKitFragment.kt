package uk.co.jakelee.firebasereference.develop.mlkit


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions
import kotlinx.android.synthetic.main.fragment_develop_ml_kit.*
import uk.co.jakelee.firebasereference.BaseFirebaseFragment
import uk.co.jakelee.firebasereference.R

class MLKitFragment : BaseFirebaseFragment() {
    override val title = R.string.title_ml_kit
    override val tutorialUrl = R.string.tutorial_ml_kit
    override val docsUrl = R.string.documentation_ml_kit
    override val firebaseUrl = R.string.firebase_ml_kit

    private val TEXT_RESPONSE = 3331
    private val FACE_RESPONSE = 3442

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_develop_ml_kit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textButton.setOnClickListener {
            startActivityForResult(
                Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), TEXT_RESPONSE)
        }
        faceButton.setOnClickListener {
            startActivityForResult(
                    Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), FACE_RESPONSE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) return
        val uri = Uri.parse(data!!.dataString)
        val image = FirebaseVisionImage.fromFilePath(activity!!, uri)
        imagePreview.setImageBitmap(image.bitmapForDebugging)
        output.text = ""
        when (requestCode) {
            TEXT_RESPONSE -> retrieveText(image)
            FACE_RESPONSE -> retrieveFace(image)
        }

    }

    private fun retrieveText(image: FirebaseVisionImage) {
        FirebaseVision.getInstance()
                .onDeviceTextRecognizer
                .processImage(image)
                .addOnSuccessListener { textObject ->
                    output.text = textObject.text
                    var blocks = 0
                    var lines = 0
                    var words = 0
                    val letters = textObject.textBlocks.sumBy { block ->
                        blocks += 1
                        block.lines.sumBy { line ->
                            lines += 1
                            line.elements.sumBy { word ->
                                words += 1
                                word.text.length
                            }
                        }
                    }
                    Toast.makeText(activity!!, "Found $blocks blocks, $lines lines, $words words, and $letters letters.", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { output.text = it.localizedMessage }
    }

    private fun retrieveFace(image: FirebaseVisionImage) {
        val options = FirebaseVisionFaceDetectorOptions.Builder()
                .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
                .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                .enableTracking()
                .build()
        FirebaseVision.getInstance()
                .getVisionFaceDetector(options)
                .detectInImage(image)
                .addOnSuccessListener { faces ->
                    if (faces.isEmpty()) {
                        output.text = getString(R.string.mlkit_no_faces)
                    } else {
                        var text = ""
                        faces.forEach {
                            text += String.format(getString(R.string.mlkit_face_data),
                                    it.trackingId,
                                    it.leftEyeOpenProbability * 100,
                                    it.rightEyeOpenProbability * 100,
                                    it.smilingProbability * 100,
                                    it.headEulerAngleY,
                                    it.headEulerAngleZ)
                        }
                        output.text = text
                    }
                }
                .addOnFailureListener { output.text = it.localizedMessage }
    }

}
