package com.hyperelement.mvvmdemo.ui.demorecyclerview.simplerow

import com.airbnb.epoxy.AsyncEpoxyController
import com.hyperelement.mvvmdemo.carSimpleRowItem
import com.hyperelement.mvvmdemo.ui.demorecyclerview.simplerow.data.CountryName

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

            }
        }
    }
}