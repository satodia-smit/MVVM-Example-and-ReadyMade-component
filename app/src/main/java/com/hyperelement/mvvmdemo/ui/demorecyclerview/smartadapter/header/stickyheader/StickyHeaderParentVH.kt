package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.header.stickyheader

import android.view.ViewGroup
import android.widget.TextView
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.data.datasources.models.generic.Continent
import smartadapter.viewholder.SmartViewHolder

//class StickyHeaderParentVH(parentView: ViewGroup) :
//    SmartViewHolder<Continent>(
//    parentView.inflate<ItemStickyHeaderParentBinding>(R.layout.item_sticky_header_parent).root
//) {
//    override fun bind(item: Continent) {
//        val binding = DataBindingUtil.getBinding<ItemStickyHeaderParentBinding>(itemView)
//        binding?.dataItem = item
//    }
//}

class StickyHeaderParentVH(parentView: ViewGroup) :
    SmartViewHolder<Continent>(parentView, R.layout.item_sticky_header_parent) {

    private val mContinentHeaderView: TextView = itemView.findViewById(R.id.txtContinentHeader)
    override fun bind(movie: Continent) {
        mContinentHeaderView.text = movie.mContinentName
    }
}

