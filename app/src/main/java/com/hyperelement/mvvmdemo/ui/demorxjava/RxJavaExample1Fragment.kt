package com.hyperelement.mvvmdemo.ui.demorxjava

import android.os.Bundle
import android.util.Log
import android.view.View
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.databinding.FragmentRxJavaExample1Binding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.fromIterable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "RxJavaExample1Fragment"

class RxJavaExample1Fragment :
    BaseFragment<RxJavaExample1VM>(R.layout.fragment_rx_java_example_1, RxJavaExample1VM::class) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentRxJavaExample1Binding>()?.viewModel = viewModel



    }
}