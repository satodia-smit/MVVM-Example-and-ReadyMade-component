package com.hyperelement.mvvmdemo.ui.demorecyclerview.simple

import com.airbnb.epoxy.EpoxyController
import com.hyperelement.mvvmdemo.data.datasources.models.generic.Car

class CarRVController : EpoxyController() {
    private var index = 0L

    private val mCarList: List<Car> = mutableListOf(Car("Ford"), Car("Honda"))
    override fun buildModels() {

        mCarList.forEach {
            SimpleCarModel_(it).id(index++).addTo(this)
        }
    }
}