package com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmentthree

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentThree : BaseFragment<FragmentThreeViewModel>(R.layout.fragment_three, FragmentThreeViewModel::class) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtA.text = "${getString(R.string.lbl_three)} ${viewModel.Str}"
    }
}