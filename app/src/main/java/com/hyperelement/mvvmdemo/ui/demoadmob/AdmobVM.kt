package com.hyperelement.mvvmdemo.ui.demoadmob

import com.hyperelement.mvvmdemo.data.repository.AdmobRepo
import com.hyperelement.mvvmdemo.utilities.RootViewModel

private const val TAG = "AdmobVM"

class AdmobVM(
    private val mRepository: AdmobRepo
) : RootViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}