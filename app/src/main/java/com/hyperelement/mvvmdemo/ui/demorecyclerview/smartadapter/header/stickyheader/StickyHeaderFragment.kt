package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.header.stickyheader

import android.os.Bundle
import android.view.View
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.data.datasources.models.generic.Continent
import com.hyperelement.mvvmdemo.data.datasources.models.generic.Country
import com.hyperelement.mvvmdemo.databinding.FragmentStickyHeaderBinding
import com.hyperelement.mvvmdemo.utilities.ExtraUtils
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

        val mJsonContinentCountry = ExtraUtils.getJsonFromAssets(requireContext(), "Continent.json")
        Timber.d("JSON STRING \n $mJsonContinentCountry")

        adapter = SmartRecyclerAdapter.empty()
            .map(Continent::class, StickyHeaderParentVH::class)
            .map(Country::class, StickyHeaderChildVH::class)
            .add(
                StickyHeaderItemDecorationExtension(
                    headerItemType = Continent::class
                )
            )
            .into(rvContinent)

        val mContinentArray = arrayListOf<Continent>()
        mContinentArray.add(
            Continent(
                "Africa",
                arrayListOf(Country("Algeria - Algiers"), Country("Angola - Luanda"))
            )
        )
        mContinentArray.add(
            Continent(
                "Africa1",
                arrayListOf(Country("Algeria - Algiers1"), Country("Angola - Luanda1"))
            )
        )

        val mArray2 = arrayListOf<Any>()
        for (continent in mContinentArray) {
            mArray2.add(Continent(continent.mContinentName))
            for (country in continent.mCountries!!) {
                mArray2.add(Country(country.mCountryName))
            }
        }

        adapter.addItems(mArray2.toMutableList())
    }
}