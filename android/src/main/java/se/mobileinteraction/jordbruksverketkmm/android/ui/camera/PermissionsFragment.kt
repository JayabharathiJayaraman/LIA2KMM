package se.mobileinteraction.jordbruksverketkmm.android.ui.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission.launch(Manifest.permission.CAMERA)
        } else {
            navigateToCamera()
        }
    }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                navigateToCamera()
            } else {
                navigateBack()
            }
        }

    private fun navigateToCamera() {
        /*Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(
            PermissionsFragmentDirections.navigateToCamera()
        )*/
    }

    private fun navigateBack() {
        /*Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(
            PermissionsFragmentDirections.navigateToMenu()
        )*/
    }
}