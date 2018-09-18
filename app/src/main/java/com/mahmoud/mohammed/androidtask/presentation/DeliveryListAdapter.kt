package com.mahmoud.mohammed.androidtask.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.mohammed.androidtask.R
import com.mahmoud.mohammed.androidtask.common.imagehelper.ImageLoader
import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel
import com.mahmoud.mohammed.androidtask.domain.emptyDeliveryViewModel
import kotlinx.android.synthetic.main.delivery_list_item.view.*


class DeliveryListAdapter constructor(private val imageLoader: ImageLoader,
                                      private val onDeliverySelected: (DeliveryViewModel, View) -> Unit) : PaginationAdapter<DeliveryViewModel>() {
    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.delivery_list_item, parent, false)
        return DeliveryViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is DeliveryViewHolder) holder.bind(dataList[position],imageLoader,onDeliverySelected)
    }
    fun removeAt(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun addLoadingViewFooter() {
        addLoadingViewFooter(emptyDeliveryViewModel)
    }
    fun updateData(newData: List<DeliveryViewModel>) {
        val fromIndex = dataList.size
        dataList = newData.toMutableList()
        notifyItemRangeInserted(fromIndex, newData.size)
    }

    class DeliveryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DeliveryViewModel, imageLoader: ImageLoader, listener:
        (DeliveryViewModel, View) -> Unit) {

            itemView.apply {

                item.imageUrl?.let {
                    imageLoader.load(it, delivery_image)
                }
                description_tv_id.text=item.description
                address_tv_id.text=item.address
                setOnClickListener { listener(item, itemView) }

            }

        }


}



}