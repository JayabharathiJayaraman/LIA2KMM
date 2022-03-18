package se.mobileinteraction.jordbruksverketkmm.android.ui.camera

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.LayoutInflater
import android.view.Surface.ROTATION_0
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentCameraBinding
import se.mobileinteraction.jordbruksverketkmm.android.databinding.PreviewCameraContainerBinding
import java.io.File
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
        fragmentCameraBinding.viewFinder.post {
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

        imageCapture =
            ImageCapture.Builder().setJpegQuality(JPEG_QUALITY).setTargetRotation(ROTATION_0)
                .build()

        val viewPort = ViewPort.Builder(
            Rational(
                fragmentCameraBinding.viewFinder.width,
                fragmentCameraBinding.viewFinder.height
            ), ROTATION_0
        ).build()

        val useCaseGroup = imageCapture?.let {
            UseCaseGroup.Builder()
                .addUseCase(preview)
                .addUseCase(it)
                .setViewPort(viewPort)
                .build()
        }
        cameraProvider?.unbindAll()
        useCaseGroup?.let { cameraProvider?.bindToLifecycle(this, cameraSelector, it) }
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
                            onImageSaved(output: ImageCapture.OutputFileResults) {
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
        return File.createTempFile(name, ".jpg", outputDir).also { cachedImageFile = it }
    }

    private fun updateUiForImagePreview(cachedImageUri: Uri?) {
        previewBinding = PreviewCameraContainerBinding.inflate(
            LayoutInflater.from(requireContext()),
            fragmentCameraBinding.root,
            true
        )

        cameraBinding?.imageCaptureButton?.isVisible = false
        cameraBinding?.viewFinder?.isVisible = false

        previewBinding?.ivPreview?.setImageURI(cachedImageUri)
        previewOnClick()
    }

    private fun previewOnClick() = previewBinding?.apply {
        btnContainerClose.setOnClickListener {
            cachedImageFile?.delete()
            view?.findNavController()?.navigateUp()
        }
        btnAcceptImage.setOnClickListener {
            updateUiForAcceptImage()
        }
        btnDeclineImage.setOnClickListener {
            updateUiForDeclineImage()
        }
    }

    private fun updateUiForDeclineImage() {
        previewBinding?.root?.let { fragmentCameraBinding.root.removeView(it) }
        cameraBinding?.imageCaptureButton?.isVisible = true
        cameraBinding?.viewFinder?.isVisible = true
        cachedImageFile?.delete()
    }

    private fun updateUiForAcceptImage() {
        saveImageToPermanentFolder()
        cachedImageFile?.delete()
    }

    private fun saveImageToPermanentFolder() {
        val imageDirectory = context?.getDir(IMAGES_FOLDER, Context.MODE_PRIVATE)
        val targetFile = File(imageDirectory, cachedImageFile?.name.toString())
        cachedImageFile?.let { it.copyTo(targetFile) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraBinding = null
        cameraExecutor.shutdown()
    }

    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val JPEG_QUALITY = 70
        private const val IMAGES_FOLDER = "Images"
    }
}
