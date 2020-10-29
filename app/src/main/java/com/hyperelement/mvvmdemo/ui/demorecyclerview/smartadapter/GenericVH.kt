package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter

import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.data.datasources.models.generic.EmployeeEntity
import com.hyperelement.mvvmdemo.databinding.ItemSimpleRowBinding
import com.hyperelement.mvvmdemo.utilities.ext.inflate
import smartadapter.viewevent.model.ViewEvent
import smartadapter.viewevent.viewholder.OnItemClickEventListener
import smartadapter.viewevent.viewholder.OnItemLongClickEventListener
import smartadapter.viewholder.SmartViewHolder


class GenericVH(var parentView: ViewGroup) : SmartViewHolder<EmployeeEntity>(
    parentView.inflate<ItemSimpleRowBinding>(R.layout.item_simple_row).root
),
    OnItemClickEventListener,
    OnItemLongClickEventListener {

    override fun bind(item: EmployeeEntity) {
        val binding = DataBindingUtil.getBinding<ItemSimpleRowBinding>(itemView)
        binding?.dataItem = item
    }

    override fun onViewEvent(event: ViewEvent.OnClick) {
        Toast.makeText(itemView.context, "SimpleEventListenerViewHolder ${event::class.simpleName} intercept", Toast.LENGTH_SHORT).show()
    }

    override fun onViewEvent(event: ViewEvent.OnLongClick) {
        Toast.makeText(itemView.context, "SimpleEventListenerViewHolder ${event::class.simpleName} intercept", Toast.LENGTH_SHORT).show()
    }

}