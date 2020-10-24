package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.loadmore

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.data.datasources.models.generic.EmployeeEntity
import com.hyperelement.mvvmdemo.databinding.ItemSimpleRowBinding
import com.hyperelement.mvvmdemo.utilities.ext.inflate
import smartadapter.SmartViewHolderType
import smartadapter.listener.OnViewEventListener
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.ViewEventListenerHolder

class LoadMoreVH(var parentView: ViewGroup) : SmartViewHolder<EmployeeEntity>(
    parentView.inflate<ItemSimpleRowBinding>(R.layout.item_simple_row).root
), ViewEventListenerHolder {
    override lateinit var viewEventListener: OnViewEventListener

    init {
        itemView.setOnClickListener {
            viewEventListener.onViewEvent(it, R.id.event_on_click, adapterPosition)
        }
    }

    override fun bind(item: EmployeeEntity) {
        val binding = DataBindingUtil.getBinding<ItemSimpleRowBinding>(itemView)
        binding?.dataItem = item
    }

    internal interface ItemClickListener : OnViewEventListener {
        override val viewHolderType: SmartViewHolderType
            get() = LoadMoreVH::class
    }
}