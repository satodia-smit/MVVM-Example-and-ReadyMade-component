package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.header.stickyheader

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.data.datasources.models.generic.Continent
import com.hyperelement.mvvmdemo.data.datasources.models.generic.Country
import com.hyperelement.mvvmdemo.databinding.FragmentStickyHeaderBinding
import kotlinx.android.synthetic.main.fragment_sticky_header.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.stickyheader.StickyHeaderItemDecorationExtension
import timber.log.Timber

class StickyHeaderFragment :
    BaseFragment<StickyHeaderVM>(
        R.layout.fragment_sticky_header,
        StickyHeaderVM::class
    ) {

    private lateinit var adapter: SmartRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentStickyHeaderBinding>()?.viewModel = viewModel

        adapter = SmartRecyclerAdapter.empty()
            .map(Continent::class, StickyHeaderParentVH::class)
            .map(Country::class, StickyHeaderChildVH::class)
            .add(
                StickyHeaderItemDecorationExtension(
                    headerItemType = Continent::class
                )
            )
            .into(rvContinent)
        viewModel.loadContinentCountry()

        viewModel.mContinentCountryList.observe(viewLifecycleOwner, Observer
        {

            val mArray = arrayListOf<Any>()
            var mOldContinent = ""
            for (continent in it.sortedWith(compareBy({ it.continent }))) {
                Timber.d("ITEM $continent")
                if (mOldContinent != continent.continent) {
                    mArray.add(Continent(continent.continent))
                    mOldContinent = continent.continent
                }
                mArray.add(Country(continent.name))
            }
            adapter.addItems(mArray.toMutableList())
        })
    }
}