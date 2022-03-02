package se.mobileinteraction.jordbruksverketkmm.android.ui.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import se.mobileinteraction.jordbruksverketkmm.android.R

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
                Log.e("DEBUG", "Permission is: $isGranted")
                navigateToCamera()
            } else {
                Log.e("DEBUG", "Permission is: $isGranted")
                navigateBack()
            }
        }

    private fun navigateToCamera() {
        lifecycleScope.launchWhenStarted {
            Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(
                PermissionsFragmentDirections.navigateToCamera())
        }
    }

    private fun navigateBack() {
        lifecycleScope.launchWhenStarted {
            Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(
                PermissionsFragmentDirections.navigateToMenu())
        }
    }
}