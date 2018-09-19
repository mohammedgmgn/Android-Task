package com.mahmoud.mohammed.androidtask.presentation.detail
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mahmoud.mohammed.androidtask.R
import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel

class DeliveryDetailsActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    private lateinit var deliveryObj:DeliveryViewModel
    companion object{
        const val DELIVERY_ID: String = "extra_movie_id"
        fun newIntent(context: Context, deliveryViewModel:DeliveryViewModel): Intent {
            val i = Intent(context, DeliveryDetailsActivity::class.java)
            i.putExtra(DELIVERY_ID,deliveryViewModel )
            return i
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

         deliveryObj=intent.getParcelableExtra(DELIVERY_ID)
        Toast.makeText(this,deliveryObj.address,Toast.LENGTH_SHORT).show()
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val location = LatLng(deliveryObj.lat, deliveryObj.lng)
        mMap.addMarker(MarkerOptions().position(location).title(deliveryObj.address))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,12.0f))
    }
}
