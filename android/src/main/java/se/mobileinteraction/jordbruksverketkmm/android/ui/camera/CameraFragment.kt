package se.mobileinteraction.jordbruksverketkmm.android.ui.camera

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentCameraBinding
import se.mobileinteraction.jordbruksverketkmm.android.databinding.PreviewCameraContainerBinding
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CameraFragment : Fragment() {

    //Binding
    private var _fragmentCameraBinding: FragmentCameraBinding? = null
    private val fragmentCameraBinding get() = _fragmentCameraBinding!!
    private var previewCameraContainerBinding: PreviewCameraContainerBinding? = null

    //CameraX
    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null
    private var cameraProvider: ProcessCameraProvider? = null

    //File
    private var cachedImageFile: File? = null
    private var savedImageUri: Uri? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

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
            _fragmentCameraBinding?.btnCameraClose?.setOnClickListener {
                view.findNavController().navigateUp()
            }
        }
    }


    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            cameraProvider = cameraProviderFuture.get()
            bindCameraUseCases()

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun bindCameraUseCases() {
        // Select back camera as a default
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        // Provide a surface for preview
        val preview = Preview.Builder()
            .build()
            .also {
                it.setSurfaceProvider(fragmentCameraBinding.viewFinder.surfaceProvider)
            }
        imageCapture = ImageCapture.Builder().setJpegQuality(70).build()

        // Unbind use cases before rebinding
        cameraProvider?.unbindAll()
        // Bind use cases to camera
        cameraProvider?.bindToLifecycle(this, cameraSelector, preview, imageCapture)

    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        imageCapture ?: return

        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())

        val outputDir = context?.cacheDir
        val imageFile = File.createTempFile(name, ".jpg", outputDir)
        cachedImageFile = imageFile

        val outputOptions = requireActivity().contentResolver?.let {
            ImageCapture.OutputFileOptions.Builder(imageFile).build()
        }

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
                        savedImageUri = output.savedUri
                        updateUiForImagePreview(savedImageUri)
                    }
                }
            )
        }
    }

    private fun updateUiForImagePreview(cachedImageUri: Uri?) {
        //Inflate preview container
        previewCameraContainerBinding = PreviewCameraContainerBinding.inflate(
            LayoutInflater.from(requireContext()),
            fragmentCameraBinding.root,
            true
        )
        _fragmentCameraBinding?.imageCaptureButton?.isVisible = false
        _fragmentCameraBinding?.viewFinder?.isVisible = false
        previewCameraContainerBinding?.ivPreview?.setImageURI(cachedImageUri)

        previewCameraContainerBinding?.btnContainerClose?.setOnClickListener {
            cachedImageFile?.delete()
            view?.findNavController()?.navigateUp()
        }
        previewCameraContainerBinding?.btnAcceptImage?.setOnClickListener {
            updateUiForAcceptImage()
        }
        previewCameraContainerBinding?.btnDeclineImage?.setOnClickListener {
            updateUiForDeclineImage()
        }
    }

    private fun updateUiForDeclineImage() {
        // Remove preview container
        previewCameraContainerBinding?.root?.let {fragmentCameraBinding.root.removeView(it)}

        _fragmentCameraBinding?.imageCaptureButton?.isVisible = true
        _fragmentCameraBinding?.viewFinder?.isVisible = true

        cachedImageFile?.delete()
    }

    private fun updateUiForAcceptImage() {

        saveImageToPermanentFolder()
        cachedImageFile?.delete()
        view?.findNavController()?.navigate(R.id.navigateFromCameraToMenu)
    }

    private fun saveImageToPermanentFolder() {
        val cacheDir = context?.cacheDir
        val cachedFile = File(cacheDir,cachedImageFile?.name.toString())

        val imageDirectory = context?.getDir("images", Context.MODE_PRIVATE)
        val imageFile= File(imageDirectory,cachedImageFile?.name.toString())

        if (imageFile.isDirectory) {
            cachedFile.copyTo(imageFile, true)
        } else {
            imageFile.mkdirs()
            cachedFile.copyTo(imageFile, true)
        }
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
