package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.loadmore

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.data.datasources.models.generic.EmployeeEntity
import com.hyperelement.mvvmdemo.databinding.FragmentLoadMoreBinding
import kotlinx.android.synthetic.main.fragment_load_more.*
import smartadapter.Position
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartadapter.ViewEventId
import timber.log.Timber

class LoadMoreFragment :
    BaseFragment<LoadMoreVM>(
        R.layout.fragment_load_more,
        LoadMoreVM::class
    ) {

    private var adapter: SmartEndlessScrollRecyclerAdapter? = null
    var currentPage:Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentLoadMoreBinding>()?.viewModel = viewModel

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = SmartEndlessScrollRecyclerAdapter.empty()
            .map(EmployeeEntity::class, LoadMoreVH::class)
            .setLayoutManager(layoutManager)
            .addViewEventListener(object : LoadMoreVH.ItemClickListener {
                override fun onViewEvent(view: View, viewEventId: ViewEventId, position: Position) {
                    val mEntity = adapter?.getItem(position) as? EmployeeEntity
                    Timber.d("CLICKED ITEM ${mEntity.toString()}")
                }
            })
            .into(rvEmployee)
        adapter?.autoLoadMoreEnabled = true

        viewModel.mEmployeeList.observe(viewLifecycleOwner, Observer {
            adapter?.addItems(it)
        })

        adapter?.onLoadMoreListener = {
            loadMore()
        }
    }

    private fun loadMore() {
        viewModel.loadEmployeesFromStorage(currentPage)
        currentPage++

    }
}