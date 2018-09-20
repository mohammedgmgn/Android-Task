package com.mahmoud.mohammed.androidtask.presentation.detail.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap

import com.mahmoud.mohammed.androidtask.R
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mahmoud.mohammed.androidtask.base.BaseFragment
import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel
import com.mahmoud.mohammed.androidtask.presentation.deliveries.fragments.DeliveriesListFragment
import com.mahmoud.mohammed.androidtask.presentation.detail.activities.DeliveryDetailsActivity
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.fragment_delivery_details.view.*


val DELIVERIES_DETAILS_FRAGMENT_TAG = DeliveryDetailsFragment::class.java.name
class DeliveryDetailsFragment : BaseFragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var deliveryObj: DeliveryViewModel
    private var mapFragment: SupportMapFragment? = null

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle) =
                DeliveryDetailsFragment().apply {
                    arguments = bundle
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            deliveryObj=arguments!!.getParcelable(DeliveryDetailsActivity.DELIVERY_ID) as DeliveryViewModel

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_delivery_details, container,
                false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment?
          mapFragment!!.getMapAsync(this);

        return view
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val location = LatLng(deliveryObj.lat, deliveryObj.lng)
        mMap.addMarker(MarkerOptions().position(location).title(deliveryObj.address))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,12.0f))
    }

}
