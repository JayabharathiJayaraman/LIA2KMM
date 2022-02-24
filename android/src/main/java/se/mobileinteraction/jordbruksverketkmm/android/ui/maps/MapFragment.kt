package se.mobileinteraction.jordbruksverketkmm.android.ui.maps

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import se.mobileinteraction.jordbruksverketkmm.android.R
import java.util.jar.Manifest

class MapFragment : Fragment(), OnMapReadyCallback
    {

        private lateinit var mMap: GoogleMap
        private lateinit var mapFragment: SupportMapFragment

        companion object {
            private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment
        mapFragment.getMapAsync({
            mMap = it
        } )
        }

        override fun onMapReady(p0: GoogleMap) {
            TODO("Not yet implemented")
        }

    }