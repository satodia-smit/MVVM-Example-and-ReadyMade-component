package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.header.stickyheader

import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.data.datasources.models.generic.Continent
import com.hyperelement.mvvmdemo.databinding.ItemStickyHeaderParentBinding
import com.hyperelement.mvvmdemo.utilities.ext.inflate
import smartadapter.viewevent.model.ViewEvent
import smartadapter.viewevent.viewholder.OnItemClickEventListener
import smartadapter.viewevent.viewholder.OnItemLongClickEventListener
import smartadapter.viewholder.SmartViewHolder

class StickyHeaderParentVH(var parentView: ViewGroup) : SmartViewHolder<Continent>(
    parentView.inflate<ItemStickyHeaderParentBinding>(R.layout.item_sticky_header_parent).root
),
    OnItemClickEventListener,
    OnItemLongClickEventListener {


    override fun bind(item: Continent) {
        val binding = DataBindingUtil.getBinding<ItemStickyHeaderParentBinding>(itemView)
        binding?.dataItem = item
    }

    override fun onViewEvent(event: ViewEvent.OnClick) {
        Toast.makeText(
            itemView.context,
            "SimpleEventListenerViewHolder ${event::class.simpleName} intercept",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onViewEvent(event: ViewEvent.OnLongClick) {
        Toast.makeText(
            itemView.context,
            "SimpleEventListenerViewHolder ${event::class.simpleName} intercept",
            Toast.LENGTH_SHORT
        ).show()
    }

}