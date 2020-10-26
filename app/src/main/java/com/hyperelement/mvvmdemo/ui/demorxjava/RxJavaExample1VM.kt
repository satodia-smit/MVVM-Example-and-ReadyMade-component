package com.hyperelement.mvvmdemo.ui.demorxjava

import com.hyperelement.mvvmdemo.data.repository.RxJavaExample1Repository
import com.hyperelement.mvvmdemo.utilities.RootViewModel

class RxJavaExample1VM(
    private val mRepository: RxJavaExample1Repository
) : RootViewModel() {

    fun loadData() = mRepository.generateData()
}