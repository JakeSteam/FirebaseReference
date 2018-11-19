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
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
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
    private val BARCODE_RESPONSE = 4443
    private val LABEL_RESPONSE = 4143

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
        barcodeButton.setOnClickListener {
            startActivityForResult(
                    Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), BARCODE_RESPONSE)
        }
        objectButton.setOnClickListener {
            startActivityForResult(
                    Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), LABEL_RESPONSE)
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
            BARCODE_RESPONSE -> retrieveBarcode(image)
            LABEL_RESPONSE -> retrieveLabels(image)
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

    private fun retrieveBarcode(image: FirebaseVisionImage) {
        val options = FirebaseVisionBarcodeDetectorOptions.Builder()
                .setBarcodeFormats(
                        FirebaseVisionBarcode.FORMAT_QR_CODE,
                        FirebaseVisionBarcode.FORMAT_AZTEC,
                        FirebaseVisionBarcode.FORMAT_UPC_A,
                        FirebaseVisionBarcode.FORMAT_UPC_E,
                        FirebaseVisionBarcode.FORMAT_EAN_13)
                .build()
        FirebaseVision.getInstance()
                .getVisionBarcodeDetector(options)
                .detectInImage(image)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isEmpty()) {
                        output.text = getString(R.string.mlkit_no_barcode)
                    } else {
                        var string = ""
                        barcodes.forEach {
                            string += String.format(getString(R.string.mlkit_barcode_data),
                                    it.rawValue,
                                    getBarcodeType(it.valueType))
                        }
                        output.text = string
                    }
                }
                .addOnFailureListener { output.text = it.localizedMessage }
    }

    private fun retrieveLabels(image: FirebaseVisionImage) {
        FirebaseVision.getInstance()
                .visionLabelDetector
                .detectInImage(image)
                .addOnSuccessListener { labels ->
                    if (labels.isEmpty()) {
                        output.text = getString(R.string.mlkit_no_label)
                    } else {
                        var string = ""
                        labels.forEach {
                            string += String.format(getString(R.string.mlkit_label_data),
                                    it.label, it.confidence * 100)
                        }
                        output.text = string
                    }
                }
                .addOnFailureListener { output.text = it.localizedMessage }
    }

    private fun getBarcodeType(valueType: Int) = when (valueType) {
        FirebaseVisionBarcode.TYPE_CONTACT_INFO -> "Contact info"
        FirebaseVisionBarcode.TYPE_EMAIL -> "Email"
        FirebaseVisionBarcode.TYPE_ISBN -> "ISBN"
        FirebaseVisionBarcode.TYPE_PHONE -> "Phone number"
        FirebaseVisionBarcode.TYPE_PRODUCT -> "Product"
        FirebaseVisionBarcode.TYPE_SMS -> "SMS"
        FirebaseVisionBarcode.TYPE_TEXT -> "Text"
        FirebaseVisionBarcode.TYPE_URL -> "URL"
        FirebaseVisionBarcode.TYPE_WIFI -> "WiFi access point"
        FirebaseVisionBarcode.TYPE_GEO -> "Coordinates"
        FirebaseVisionBarcode.TYPE_CALENDAR_EVENT -> "Event"
        FirebaseVisionBarcode.TYPE_DRIVER_LICENSE -> "Driver's license"
        else -> "Unknown"
    }

}
