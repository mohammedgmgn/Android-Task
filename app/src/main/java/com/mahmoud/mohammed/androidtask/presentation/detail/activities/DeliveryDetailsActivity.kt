package com.mahmoud.mohammed.androidtask.presentation.detail.activities
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mahmoud.mohammed.androidtask.R
import com.mahmoud.mohammed.androidtask.common.replaceFragment
import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel
import com.mahmoud.mohammed.androidtask.presentation.deliveries.activities.DeliveriesActivity
import com.mahmoud.mohammed.androidtask.presentation.deliveries.fragments.DELIVERIES_LIST_FRAGMENT_TAG
import com.mahmoud.mohammed.androidtask.presentation.deliveries.fragments.newDeliveriesListFragment
import com.mahmoud.mohammed.androidtask.presentation.detail.fragments.DELIVERIES_DETAILS_FRAGMENT_TAG
import com.mahmoud.mohammed.androidtask.presentation.detail.fragments.DeliveryDetailsFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DeliveryDetailsActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var toolbar: Toolbar

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
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
        setContentView(R.layout.activity_delivery_details)
        toolbar = findViewById(R.id.detail_toolbar_id)
        toolbar.setTitle(R.string.delivery_detail)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener {
            finish()
        }

        if (savedInstanceState == null)
        {
            val bundle=Bundle()
             bundle.putParcelable(DELIVERY_ID, intent.getParcelableExtra(DELIVERY_ID))
            replaceFragment(R.id.container, DeliveryDetailsFragment.newInstance(bundle), DELIVERIES_DETAILS_FRAGMENT_TAG)
        }
    }


}
