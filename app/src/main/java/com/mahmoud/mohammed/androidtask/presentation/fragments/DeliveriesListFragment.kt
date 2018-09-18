package com.mahmoud.mohammed.androidtask.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.mohammed.androidtask.R
import com.mahmoud.mohammed.androidtask.common.hasNetwork
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

class DeliveriesListFragment : Fragment() {
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

    private val stateObserver = Observer<DeliveryListState> { state ->
        state?.let {
            isLastPage = state.loadedAllItems
            when (state) {
                is DefaultState -> {
                    isLoading = false
                    swipeRefreshLayout.isRefreshing = false
                    updateListData(it.data)

                }
                is LoadingState -> {
                    swipeRefreshLayout.isRefreshing = true
                    isLoading = true
                }
                is PaginatingState -> {
                    isLoading = true
                }
                is ErrorState -> {
                    // handleErrorState(it.data)
                    isLoading = false
                    swipeRefreshLayout.isRefreshing = false
                    deliveryListAdapter.removeLoadingViewFooter()
                }
            }
        }
    }

    private fun updateListData(data: List<DeliveryViewModel>) {
      /*  if(recyclerView.visibility==GONE)
        {
            recyclerView.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.GONE);
        }*/
            deliveryListAdapter.updateData(data)


    }
/*
    private fun handleErrorState(data: List<DeliveryViewModel>) {
        if (data.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            empty_view.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.GONE);
        }
    }
*/

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
        } ?: viewModel.updateDeliveryList()
    }

    private fun checkConnection(savedInstanceState: Bundle?) {
        if (!hasNetwork(context!!)!! && savedInstanceState == null) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);


        }
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
        initializeSwipeToRefreshView(view)
        //checkConnection(savedInstanceState)

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


}
