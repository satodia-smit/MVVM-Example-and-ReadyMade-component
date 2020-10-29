package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.loadmore

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.data.datasources.models.generic.EmployeeEntity
import com.hyperelement.mvvmdemo.databinding.FragmentLoadMoreBinding
import com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.GenericVH
import com.hyperelement.mvvmdemo.utilities.ext.runDelayed
import com.hyperelement.mvvmdemo.utilities.ext.showToast
import kotlinx.android.synthetic.main.fragment_load_more.*
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartadapter.viewevent.listener.OnClickEventListener
import timber.log.Timber

class LoadMoreFragment :
    BaseFragment<LoadMoreVM>(
        R.layout.fragment_load_more,
        LoadMoreVM::class
    ) {

    private var adapter: SmartEndlessScrollRecyclerAdapter? = null
    private var currentPage: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentLoadMoreBinding>()?.viewModel = viewModel

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        adapter = SmartEndlessScrollRecyclerAdapter.empty()
            .setAutoLoadMoreEnabled(true)
            .setLoadMoreLayoutResource(R.layout.custom_load_more_view)
            .setOnLoadMoreListener { adapter, loadMoreViewHolder ->
                loadMore()
            }
            .map(EmployeeEntity::class, GenericVH::class)
            .setLayoutManager(layoutManager)
            .add(OnClickEventListener {
                val mEntity = adapter?.getItem(it.position) as? EmployeeEntity
                Timber.d("CLICKED ITEM ${mEntity.toString()}")
                context?.showToast("onItemClick ${it.position}")
            })
            .into(rvEmployee)
        loadMore()

        viewModel.mEmployeeList.observe(viewLifecycleOwner, Observer {
            adapter?.addItems(it)
        })
    }

    private fun loadMore() {
        runDelayed {
            viewModel.loadEmployeesFromStorage(currentPage)
            currentPage++
        }
    }


}