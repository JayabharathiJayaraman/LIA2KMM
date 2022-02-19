package se.mobileinteraction.jordbruksverketkmm.android.ui.camera

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import se.mobileinteraction.jordbruksverketkmm.android.databinding.CameraUiContainerBinding
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentCameraBinding
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraFragment : Fragment() {

    private var _fragmentCameraBinding: FragmentCameraBinding? = null
    private val fragmentCameraBinding get() = _fragmentCameraBinding!!
    private var cameraUiContainerBinding: CameraUiContainerBinding? = null
    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentCameraBinding = FragmentCameraBinding.inflate(inflater, container, false)
        return fragmentCameraBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
        fragmentCameraBinding.viewFinder.post{
            startCamera()
            _fragmentCameraBinding?.imageCaptureButton?.setOnClickListener {
                takePhoto()
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(activity?.applicationContext!!)

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(fragmentCameraBinding.viewFinder.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder().build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)

            } catch(exc: Exception) {
                Log.e("DEBUG", "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(activity?.applicationContext!!))
    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        imageCapture ?: return

        // Create time stamped name and File entry.
        val name = SimpleDateFormat(FILENAME_FORMAT,  Locale.US)
            .format(System.currentTimeMillis())
        val photoFile = File(context?.filesDir, "$name.jpg")

        // Create output options object which contains file + metadata
        val outputOptions = activity?.contentResolver?.let {
            ImageCapture.OutputFileOptions.Builder(photoFile).build()
        }

        // Set up image capture listener, which is triggered after photo has
        // been taken
        outputOptions?.let {
            imageCapture?.takePicture(
                it,
                ContextCompat.getMainExecutor(requireContext()),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {
                        Log.e("DEBUG", "Photo capture failed: ${exc.message}", exc)
                    }

                    override fun
                            onImageSaved(output: ImageCapture.OutputFileResults){
                        val msg = "Photo capture succeeded: ${output.savedUri}"
                        // Toast.makeText(activity?.applicationContext, msg, Toast.LENGTH_SHORT).show()
                        updateUiForImagePreview(output.savedUri)
                        Log.d("DEBUG", msg)
                    }
                }
            )
        }
    }

    private fun updateUiForImagePreview(outputUri: Uri?) {
        cameraUiContainerBinding = CameraUiContainerBinding.inflate(
            LayoutInflater.from(requireContext()),
            fragmentCameraBinding.root,
            true
        )
        _fragmentCameraBinding?.imageCaptureButton?.isVisible = false
        _fragmentCameraBinding?.viewFinder?.isVisible = false
        cameraUiContainerBinding?.ivPreview?.setImageURI(outputUri)

        cameraUiContainerBinding?.btnAcceptImage?.setOnClickListener {
            updateUiForAcceptImage()
        }
        cameraUiContainerBinding?.btnDeclineImage?.setOnClickListener {
            updateUiForDeclineImage()
        }
    }

    private fun updateUiForDeclineImage() {
        // Remove previous UI if any
        cameraUiContainerBinding?.root?.let {
            fragmentCameraBinding.root.removeView(it)
            _fragmentCameraBinding?.imageCaptureButton?.isVisible = true
            _fragmentCameraBinding?.viewFinder?.isVisible = true
        }
    }

    private fun updateUiForAcceptImage() {
        TODO("Implement sending Uri to fragment for viewing the image")

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentCameraBinding = null
        cameraExecutor.shutdown()
    }

    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }
}
