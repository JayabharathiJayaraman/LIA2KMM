package se.mobileinteraction.jordbruksverketkmm.android.ui.googlemap

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

class MapPermissionFragment : Fragment() {
    private val permReqLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value == true
            }
            if (granted) {
                displayMapFragment()
            }
        }

    private fun requestMapPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            displayMapFragment()
        }
        activity?.let {
            if (hasPermissions(activity as Context,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                )) {
                displayMapFragment()
            } else {
                permReqLauncher.launch(
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                )
            }
        }
    }

    // util method
    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun displayMapFragment() {
        // open camera fragment
    }
}