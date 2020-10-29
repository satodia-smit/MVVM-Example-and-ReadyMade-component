package com.hyperelement.mvvmdemo.ui.demorecyclerview.simple

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import kotlinx.android.synthetic.main.fragment_simple_rv.*

class SimpleRVDemoFragment :
    BaseFragment<SimpleRVDemoViewModel>(R.layout.fragment_simple_rv, SimpleRVDemoViewModel::class) {


    private val carRVController: CarRVController by lazy { CarRVController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(context)
        rvCar.apply {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = carRVController.adapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    linearLayoutManager.orientation
                )
            )
        }

        //This statement builds model and add it to the recycler view
        carRVController.requestModelBuild()
    }
}