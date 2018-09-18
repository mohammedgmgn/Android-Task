package com.mahmoud.mohammed.androidtask.presentation.fragments

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.mohammed.androidtask.R
import com.mahmoud.mohammed.androidtask.common.NetworkStateReceiver
import com.mahmoud.mohammed.androidtask.common.getCachSize
import com.mahmoud.mohammed.androidtask.common.imagehelper.ImageLoader
import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel
import com.mahmoud.mohammed.androidtask.domain.LIMIT_DELIVERY_LIST
import com.mahmoud.mohammed.androidtask.presentation.*
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_deliveries_list.*
import kotlinx.android.synthetic.main.fragment_deliveries_list.view.*
import javax.inject.Inject

fun newDeliveriesListFragment() = DeliveriesListFragment()
val DELIVERIES_LIST_FRAGMENT_TAG = DeliveriesListFragment::class.java.name

class DeliveriesListFragment : Fragment(), NetworkStateReceiver.NetworkStateReceiverListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DeliveryListViewModel
    @Inject
    lateinit var imageLoader: ImageLoader

    private val deliveryListAdapter by lazy { DeliveryListAdapter(imageLoader) }
    private var isLoading = false
    private var isLastPage = false
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: TextView
    private var networkStateReceiver: NetworkStateReceiver? = null

    private val stateObserver = Observer<DeliveryListState> { state ->
        state?.let {
            isLastPage = state.loadedAllItems
            when (state) {
                is DefaultState -> {
                    handleDefaultState(it.data)
                }
                is LoadingState -> {
                    handleLoadingState()
                }
                is PaginatingState -> {
                    handlePaginatingState()
                }
                is ErrorState -> {
                    handleErrorState()
                }
            }
        }
    }

    private fun handleErrorState() {
        Toast.makeText(context, "Something went Wrong", Toast.LENGTH_SHORT).show()
        isLoading = false
        swipeRefreshLayout.isRefreshing = false
        deliveryListAdapter.removeLoadingViewFooter()
    }

    private fun handlePaginatingState() {
        isLoading = true

    }

    private fun handleLoadingState() {
        swipeRefreshLayout.isRefreshing = true
        isLoading = true

    }

    private fun handleDefaultState(data: List<DeliveryViewModel>) {
        isLoading = false
        swipeRefreshLayout.isRefreshing = false
        updateListData(data)


    }

    private fun updateListData(data: List<DeliveryViewModel>) {
        deliveryListAdapter.updateData(data)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DeliveryListViewModel::class.java)
        observeViewModel()
        savedInstanceState?.let {
            viewModel.restoreDeliveryList()
        } //?: viewModel.updateDeliveryList()
    }


    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this, stateObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_deliveries_list, container, false)
        initializeToolbar(view)
        initializeRecyclerView(view)
        setNetworkStateReceiver()
        initializeSwipeToRefreshView(view)

        return view
    }

    private fun initializeToolbar(view: View) {
        view.toolbar.title = getString(R.string.main_title)
    }

    private fun initializeRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView_id)
        emptyView = view.findViewById(R.id.empty_view)
        val vLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.apply {
            recyclerView.layoutManager = vLayoutManager
            recyclerView.adapter = deliveryListAdapter
            addOnScrollListener(OnScrollListener(vLayoutManager))
        }


    }

    private fun loadNextPage() {
        deliveryListAdapter.addLoadingViewFooter()
        viewModel.updateDeliveryList()
    }

    private fun initializeSwipeToRefreshView(view: View) {
        view.swipeRefreshLayout.setOnRefreshListener { viewModel.restoreDeliveryList() }
    }

    inner class OnScrollListener(layoutManager: LinearLayoutManager) : PaginationScrollListener(layoutManager) {
        override fun isLoading() = isLoading
        override fun loadMoreItems() = loadNextPage()
        override fun getTotalPageCount() = LIMIT_DELIVERY_LIST
        override fun isLastPage() = isLastPage
    }

    //function that sets the network state receiver to the activity
    private fun setNetworkStateReceiver() {
        networkStateReceiver = NetworkStateReceiver(this.context!!)
        networkStateReceiver!!.addListener(this)
        activity!!.applicationContext.registerReceiver(networkStateReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }

    override fun onNetworkAvailable() {

        if (recyclerView.visibility == GONE) {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        viewModel.updateDeliveryList()
        Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show()

    }

    override fun onNetworkUnavailable() {
        Toast.makeText(context, getCachSize(context!!).toString(), Toast.LENGTH_SHORT).show()
        if (getCachSize(context!!) == 0L) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

}
