package com.hyperelement.mvvmdemo.ui.demorecyclerview.simplerow

import android.util.Log
import com.airbnb.epoxy.AsyncEpoxyController
import com.hyperelement.mvvmdemo.carSimpleRowItem
import com.hyperelement.mvvmdemo.ui.demorecyclerview.simplerow.data.CountryName
import smartadapter.listener.onItemClickListener

private const val TAG = "SimpleRowRVController"
class SimpleRowRVController : AsyncEpoxyController() {
    var mCountryName: List<CountryName> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {

        mCountryName.forEach {
            carSimpleRowItem {
                id(it.id)
                data(it)
                onItemClickListener { view, viewEventId, position ->
                    Log.d(
                        TAG,
                        "buildModels() called with: view = $view, viewEventId = $viewEventId, position = $position"
                    )
                }
            }
        }
    }
}