package se.mobileinteraction.jordbruksverketkmm.android.ui.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import se.mobileinteraction.jordbruksverketkmm.android.R
import se.mobileinteraction.jordbruksverketkmm.android.databinding.FragmentMapBinding

class MapFragment : Fragment(), OnMapReadyCallback {
    private var fragmentMapBinding: FragmentMapBinding? = null
    private lateinit var map: GoogleMap
    private var wayPointCircle: Circle? = null
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_map, container, false)
        val binding = FragmentMapBinding.bind(rootView)
        fragmentMapBinding = binding
        fragmentMapBinding?.wayPointLatitude?.text = "lat:"
        fragmentMapBinding?.wayPointLongitude?.text = "lon:"
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        requestMapPermission()
    }

    private val permReqLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value == true
            }
            if (granted) {
                showCurrentLocation()
            }
        }

    private fun requestMapPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            showCurrentLocation()
        }
        activity?.let {
            if (hasPermissions(activity as Context,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                )) {
                showCurrentLocation()
            } else {
                permReqLauncher.launch(
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                )
                showSwedenMap()
            }
        }
    }

    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED

    }

    private fun showSwedenMap() {
        val latLngSweden = LatLng(59.334591, 18.063240)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngSweden, 3.5F))
        map.uiSettings.isZoomControlsEnabled = true
    }

    @SuppressLint("MissingPermission")
    private fun showCurrentLocation() {
        fusedLocationClient.lastLocation?.addOnSuccessListener {
            if (it == null) {
            } else it.apply {
                val currentLatitude = it.latitude
                val currentLongitude = it.longitude
                val currentLocation = LatLng(currentLatitude, currentLongitude)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 18F))
                map.addCircle(
                    CircleOptions()
                        .center(LatLng(currentLatitude, currentLongitude))
                        .radius(6.0)
                        .strokeWidth(2F)
                        .strokeColor(Color.WHITE)
                        .fillColor(Color.parseColor("#4169e1"))
                )
            }
        }
        setWayPoint()
    }

    private fun setWayPoint() {
        map.setOnMapClickListener { wayPoint ->
            clearWayPoint()
            val wayPointLatitude = wayPoint.latitude
            val wayPointLongitude = wayPoint.longitude
            val wayPointLocation = LatLng(wayPointLatitude, wayPointLongitude)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(wayPointLocation, 18F))
            wayPointCircle = map.addCircle(
                CircleOptions()
                    .center(LatLng(wayPointLatitude, wayPointLongitude))
                    .radius(6.0)
                    .strokeWidth(2F)
                    .strokeColor(Color.WHITE)
                    .fillColor(Color.YELLOW)
            )

            fragmentMapBinding?.wayPointLatitude?.text = "lat: $wayPointLatitude"
            fragmentMapBinding?.wayPointLongitude?.text = "lon: $wayPointLongitude"

        }
    }

    private fun clearWayPoint() {
        if (wayPointCircle != null) {
            wayPointCircle!!.remove();
        }
    }

    /*override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSION_FINE_LOCATION -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    showCurrentLocation()
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            requireActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    ) {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            MY_PERMISSION_FINE_LOCATION
                        )
                    } else {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            MY_PERMISSION_FINE_LOCATION
                        )
                    }
                }
            } else {
                showSwedenMap()
            }
        }
    }*/
}