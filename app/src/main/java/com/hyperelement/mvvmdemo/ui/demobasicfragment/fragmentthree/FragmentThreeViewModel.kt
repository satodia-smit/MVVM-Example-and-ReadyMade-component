package com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmentthree

import com.hyperelement.mvvmdemo.data.repository.FragmentThreeRepository
import com.hyperelement.mvvmdemo.utilities.RootViewModel

class FragmentThreeViewModel(
    private val fragmentThreeRepository: FragmentThreeRepository
) : RootViewModel() {
    val Str = "Printed"
}