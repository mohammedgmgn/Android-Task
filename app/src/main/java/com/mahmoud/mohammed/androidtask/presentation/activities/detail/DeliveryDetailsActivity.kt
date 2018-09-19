package com.mahmoud.mohammed.androidtask.presentation.activities.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mahmoud.mohammed.androidtask.R
import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel

class DeliveryDetailsActivity : AppCompatActivity() {
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
        val deliveryObj=intent.getParcelableExtra<DeliveryViewModel>(DELIVERY_ID)
        Toast.makeText(this,deliveryObj.address,Toast.LENGTH_SHORT).show()
    }
}
