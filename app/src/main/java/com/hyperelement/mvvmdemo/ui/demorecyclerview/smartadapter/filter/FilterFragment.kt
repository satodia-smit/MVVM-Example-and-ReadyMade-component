package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.filter

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.data.datasources.models.generic.EmployeeEntity
import com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.GenericVH
import com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.GenericVM
import com.hyperelement.mvvmdemo.utilities.ext.showToast
import kotlinx.android.synthetic.main.fragment_filter.*
import kotlinx.android.synthetic.main.fragment_load_more.rvEmployee
import smartadapter.SmartRecyclerAdapter
import smartadapter.filter.FilterExtension
import smartadapter.get
import smartadapter.viewevent.listener.OnClickEventListener
import timber.log.Timber

class FilterFragment :
    BaseFragment<GenericVM>(
        R.layout.fragment_filter,
        GenericVM::class
    ) {
    private lateinit var adapter: SmartRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SmartRecyclerAdapter.empty()
            .map(EmployeeEntity::class, GenericVH::class)
            .add(OnClickEventListener {
                val mEntity = adapter.getItem(it.position) as? EmployeeEntity
                Timber.d("CLICKED ITEM ${mEntity.toString()}")
                context?.showToast("onItemClick ${it.position}")
            })
            .add(
                FilterExtension(
                    filterPredicate = { item, constraint ->
                        Timber.d("SEARCHED ITEM $item | $constraint")
                        when (item) {
                            is EmployeeEntity -> item.name.toLowerCase()
                                .contains(constraint.toString().toLowerCase())
                            else -> true
                        }
                    }
                ) {
                }
            )
            .into(rvEmployee)

        viewModel.loadEmployeesFromStorage()

        viewModel.mEmployeeList.observe(viewLifecycleOwner, Observer {
            adapter.addItems(it)
        })
        svEmployee.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })
    }

    fun filter(query: String?) {
        val filterExtension: FilterExtension = adapter.get()
        filterExtension.filter(lifecycleScope, query, autoSetNewItems = true)
    }
}