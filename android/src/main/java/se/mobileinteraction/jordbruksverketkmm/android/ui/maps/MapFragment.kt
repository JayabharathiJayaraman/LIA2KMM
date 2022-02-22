package se.mobileinteraction.jordbruksverketkmm.android.ui.maps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import se.mobileinteraction.jordbruksverketkmm.android.R

class MapFragment : Fragment() {

    private lateinit var mMap: GoogleMap

    companion object {
        private val MY_PERMISSION_FINE_LOCATION = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)

        val mapFragment = getFragmentManager()?.findFragmentById(R.id.googleMap) as SupportMapFragment





    }

}