package com.mahmoud.mohammed.androidtask.presentation.detail.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mahmoud.mohammed.androidtask.R
import com.mahmoud.mohammed.androidtask.common.replaceFragment
import com.mahmoud.mohammed.androidtask.data.model.DeliveryModel
import com.mahmoud.mohammed.androidtask.presentation.detail.fragments.DELIVERIES_DETAILS_FRAGMENT_TAG
import com.mahmoud.mohammed.androidtask.presentation.detail.fragments.DeliveryDetailsFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DeliveryDetailsActivity : AppCompatActivity(), HasSupportFragmentInjector {
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var deliveryDetailsFragment: DeliveryDetailsFragment

    private lateinit var toolbar: Toolbar

    companion object {
        const val DELIVERY_ID: String = "extra_delivery_id"
        fun newIntent(context: Context, deliveryViewModel: DeliveryModel): Intent {
            val i = Intent(context, DeliveryDetailsActivity::class.java)
            i.putExtra(DELIVERY_ID, deliveryViewModel)
            return i
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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

        if (savedInstanceState == null) {
            val bundle = Bundle()
            bundle.putParcelable(DELIVERY_ID, intent.getParcelableExtra(DELIVERY_ID))
            deliveryDetailsFragment.apply {
                arguments = bundle
            }
            replaceFragment(R.id.container, deliveryDetailsFragment, DELIVERIES_DETAILS_FRAGMENT_TAG)
        }
    }


}
