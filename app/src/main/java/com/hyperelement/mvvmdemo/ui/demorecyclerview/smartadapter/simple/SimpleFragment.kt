package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.simple

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.data.datasources.models.generic.EmployeeEntity
import com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.GenericVH
import com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.GenericVM
import com.hyperelement.mvvmdemo.utilities.ext.showToast
import kotlinx.android.synthetic.main.fragment_load_more.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewevent.listener.OnClickEventListener
import timber.log.Timber

class SimpleFragment :
    BaseFragment<GenericVM>(
        R.layout.fragment_simple,
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
            .into(rvEmployee)

        viewModel.loadEmployeesFromStorage()

        viewModel.mEmployeeList.observe(viewLifecycleOwner, Observer {
            adapter.addItems(it)
        })
    }
}