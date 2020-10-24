package com.hyperelement.mvvmdemo.ui.demorecyclerview.simple

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.KotlinHolder
import com.hyperelement.mvvmdemo.data.datasources.models.generic.Car

/**
 * This class needs to be abstract as Epoxy inherits this class to form SingleFoodModel_() class.
 * Also this is one of the ways a Epoxy model can be written.
 * For more visit:
 * https://github.com/airbnb/epoxy/wiki/Epoxy-Models
 */
@EpoxyModelClass(layout = R.layout.item_single)
abstract class SimpleCarModel(@EpoxyAttribute var car: Car) :
    EpoxyModelWithHolder<SimpleCarModel.FoodHolder>() {

    override fun bind(holder: FoodHolder) {
        holder.titleView.text = car.name
    }

    /**
     * This is ViewHolder class equivalent to Google's RecyclerView.ViewHolder class
     */
    inner class FoodHolder : KotlinHolder() {

        val titleView by bind<TextView>(R.id.title)

    }

}


