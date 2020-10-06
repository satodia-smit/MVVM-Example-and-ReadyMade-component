package com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmenttwo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.databinding.FragmentTwoBinding
import kotlinx.android.synthetic.main.fragment_two.*

class FragmentTwo :
    BaseFragment<FragmentTwoViewModel>(R.layout.fragment_two, FragmentTwoViewModel::class) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentTwoBinding>()?.viewModel = viewModel

        txtB.text = "${getString(R.string.lbl_two)} ${viewModel.Str}"
    }
}