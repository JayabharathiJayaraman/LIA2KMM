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
    private var cameraBinding: FragmentCameraBinding? = null
    private val fragmentCameraBinding get() = cameraBinding!!
    private var previewBinding: PreviewCameraContainerBinding? = null
    private var imageCapture: ImageCapture? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private var cachedImageFile: File? = null
    private lateinit var cameraExecutor: ExecutorService

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
        cameraBinding = FragmentCameraBinding.inflate(inflater, container, false)
        return fragmentCameraBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
        fragmentCameraBinding.viewFinder.post{
            startCamera()
            cameraBinding?.imageCaptureButton?.setOnClickListener {
                takePhoto()
            }
            cameraBinding?.btnCameraClose?.setOnClickListener {
                view.findNavController().navigateUp()
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()
            bindCameraUseCases()
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun bindCameraUseCases() {
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        val preview = Preview.Builder()
            .build()
            .also {
                it.setSurfaceProvider(fragmentCameraBinding.viewFinder.surfaceProvider)
            }
        imageCapture = ImageCapture.Builder().setJpegQuality(70).build()
        cameraProvider?.unbindAll()
        cameraProvider?.bindToLifecycle(this, cameraSelector, preview, imageCapture)
    }

    private fun takePhoto() {
        imageCapture ?: return

        val outputOptions = requireActivity().contentResolver?.let {
            ImageCapture.OutputFileOptions.Builder(createFile()).build()
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
                        updateUiForImagePreview(output.savedUri)
                    }
                }
            )
        }
    }

    private fun createFile(): File {
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val outputDir = context?.cacheDir
        cachedImageFile = File.createTempFile(name, ".jpg", outputDir)
        return cachedImageFile as File
    }

    private fun updateUiForImagePreview(cachedImageUri: Uri?) {
        previewBinding = PreviewCameraContainerBinding.inflate(
            LayoutInflater.from(requireContext()),
            fragmentCameraBinding.root, true)

        cameraBinding?.imageCaptureButton?.isVisible = false
        cameraBinding?.viewFinder?.isVisible = false

        previewBinding?.ivPreview?.setImageURI(cachedImageUri)
        previewOnClick()
    }

    private fun previewOnClick() {
        previewBinding?.btnContainerClose?.setOnClickListener {
            cachedImageFile?.delete()
            view?.findNavController()?.navigateUp()
        }
        previewBinding?.btnAcceptImage?.setOnClickListener {
            updateUiForAcceptImage()
        }
        previewBinding?.btnDeclineImage?.setOnClickListener {
            updateUiForDeclineImage()
        }
    }

    private fun updateUiForDeclineImage() {
        previewBinding?.root?.let {fragmentCameraBinding.root.removeView(it)}
        cameraBinding?.imageCaptureButton?.isVisible = true
        cameraBinding?.viewFinder?.isVisible = true

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
        cameraBinding = null
        cameraExecutor.shutdown()
    }

    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }
}
