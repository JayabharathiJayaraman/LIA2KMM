package se.mobileinteraction.jordbruksverketkmm.android.ui.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

class MapFragment : Fragment(), OnMapReadyCallback {
    companion object {
        private val MY_PERMISSION_FINE_LOCATION = 101
    }
    private lateinit var map: GoogleMap
    private var wayPointCircle: Circle? = null
    private lateinit var wayPointLat: TextView
    private lateinit var wayPointLon: TextView
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_map, container, false)
        wayPointLat = rootView.findViewById<TextView>(R.id.wayPointLatitude)
        wayPointLon = rootView.findViewById<TextView>(R.id.wayPointLongitude)
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

    private fun requestMapPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            showCurrentLocation()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSION_FINE_LOCATION
                )
            }
        }
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
            wayPointLat.text = "lon:" + wayPointLatitude.toString()
            wayPointLon.text = "lat:" + wayPointLongitude.toString()
        }
    }

    private fun clearWayPoint() {
        if (wayPointCircle != null) {
            wayPointCircle!!.remove();
        }
    }

    override fun onRequestPermissionsResult(
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
    }
}