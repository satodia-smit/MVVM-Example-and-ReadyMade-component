package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.header.stickyheader

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.data.datasources.models.generic.Continent
import com.hyperelement.mvvmdemo.data.datasources.models.generic.Country
import com.hyperelement.mvvmdemo.databinding.FragmentStickyHeaderBinding
import com.hyperelement.mvvmdemo.utilities.ext.showToast
import kotlinx.android.synthetic.main.fragment_sticky_header.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.stickyheader.StickyHeaderItemDecorationExtension

class StickyHeaderFragment :
    BaseFragment<StickyHeaderVM>(
        R.layout.fragment_sticky_header,
        StickyHeaderVM::class
    ) {

    private var adapter: SmartRecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentStickyHeaderBinding>()?.viewModel = viewModel

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

//        val mContinentArray = arrayListOf<Continent>()
//        mContinentArray.add(
//            Continent(
//                "Africa",
//                arrayListOf(Country("Algeria - Algiers"), Country("Angola - Luanda"))
//            )
//        )
//        mContinentArray.add(
//            Continent(
//                "Africa2",
//                arrayListOf(Country("Algeria - Algiers2"), Country("Angola - Luanda2"))
//            )
//        )
        val mContinentArray = mutableListOf<String>()
        mContinentArray.add("Header")
        for (i in 1..100) {
            mContinentArray.add("Item $i")
        }


//
//        val mContinentArray = mutableListOf<Any>()
//        mContinentArray.add(Continent("A"))
//        mContinentArray.add(Country("ax"))
//        mContinentArray.add(Country("bx"))
//
//        mContinentArray.add(Continent("B"))
//        mContinentArray.add(Country("ay"))
//        mContinentArray.add(Country("bx"))


        val items = mContinentArray.mapIndexed { index, item ->
            when (index) {
                0 -> arrayOf(Continent(item))
                else -> arrayOf(Country(item))
            }
        }.toTypedArray().flatten()

        adapter = SmartRecyclerAdapter
            .items(items)
            .map(Continent::class, StickyHeaderParentVH::class)
            .map(Country::class, StickyHeaderChildVH::class)
            .setLayoutManager(layoutManager)
            .add(StickyHeaderItemDecorationExtension(
                headerItemType = StickyHeaderParentVH::class
            ) { motionEvent, itemPosition ->
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    context?.showToast("Header $itemPosition clicked")
                }
            })
            .into(rvContinent)
    }
}