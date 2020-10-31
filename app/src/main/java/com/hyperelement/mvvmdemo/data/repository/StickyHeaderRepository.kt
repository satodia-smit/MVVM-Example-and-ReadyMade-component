package com.hyperelement.mvvmdemo.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hyperelement.mvvmdemo.data.datasources.models.generic.ContinentCountry
import com.hyperelement.mvvmdemo.utilities.ExtraUtils
import java.lang.reflect.Type


class StickyHeaderRepository(
    private val context: Context
) {

    suspend fun getContinentCountry(): List<ContinentCountry> {
        val mJsonContinentCountry = ExtraUtils.getJsonFromAssets(context, "Continent.json")
        val mContinentCountryListType: Type =
            object : TypeToken<ArrayList<ContinentCountry>>() {}.type

        return Gson().fromJson(mJsonContinentCountry, mContinentCountryListType)
    }
}