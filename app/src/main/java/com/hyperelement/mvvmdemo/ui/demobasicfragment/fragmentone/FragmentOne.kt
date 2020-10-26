package com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmentone

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.databinding.FragmentOneBinding
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne :
    BaseFragment<FragmentOneViewModel>(R.layout.fragment_one, FragmentOneViewModel::class) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentOneBinding>()?.viewModel = viewModel

        txtA.text = "${getString(R.string.lbl_one)} ${viewModel.Str}"
    }
}