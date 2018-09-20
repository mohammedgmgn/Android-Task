package com.mahmoud.mohammed.androidtask.presentation.detail.fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mahmoud.mohammed.androidtask.R
import com.mahmoud.mohammed.androidtask.base.BaseFragment
import com.mahmoud.mohammed.androidtask.common.imagehelper.PicassoImageLoader
import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel
import com.mahmoud.mohammed.androidtask.presentation.detail.activities.DeliveryDetailsActivity
import com.squareup.picasso.Picasso


val DELIVERIES_DETAILS_FRAGMENT_TAG = DeliveryDetailsFragment::class.java.name

class DeliveryDetailsFragment : BaseFragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var deliveryViewModel: DeliveryViewModel
    private lateinit var deliveryIv: ImageView
    private lateinit var descriptionTv: TextView
    private lateinit var addressTv: TextView


    lateinit var imageLoader: PicassoImageLoader


    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle) =
                DeliveryDetailsFragment().apply {
                    arguments = bundle
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageLoader = PicassoImageLoader(Picasso.with(context))
        arguments?.let {
            deliveryViewModel = arguments!!.getParcelable(DeliveryDetailsActivity.DELIVERY_ID) as DeliveryViewModel

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_delivery_details, container,
                false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment?
        mapFragment!!.getMapAsync(this);

        initViews(view)

        return view
    }

    @SuppressLint("NewApi")
    private fun initViews(view: View?) {
        deliveryIv = view!!.findViewById(R.id.delivery_image_id)
        descriptionTv = view.findViewById(R.id.description_tv_id)
        addressTv = view.findViewById(R.id.address_tv_id)
        descriptionTv.text = deliveryViewModel.description
        addressTv.text = deliveryViewModel.address
        imageLoader.load(deliveryViewModel.imageUrl, deliveryIv)


    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val location = LatLng(deliveryViewModel.lat, deliveryViewModel.lng)
        mMap.addMarker(MarkerOptions().position(location).title(deliveryViewModel.address))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15.0f))
    }

}
