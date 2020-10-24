package com.hyperelement.mvvmdemo.ui.demorecyclerview.simplerow

import android.os.Bundle
import android.view.View
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.ui.demorecyclerview.simplerow.data.Data
import kotlinx.android.synthetic.main.fragment_simple_row_rv.*

class SimpleRowRVDemoFragment : BaseFragment<SimpleRowRVDemoViewModel>(
    R.layout.fragment_simple_row_rv,
    SimpleRowRVDemoViewModel::class
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = SimpleRowRVController()
        rvCountry.setController(controller)

        controller.mCountryName = Data.mCountryName
    }
}