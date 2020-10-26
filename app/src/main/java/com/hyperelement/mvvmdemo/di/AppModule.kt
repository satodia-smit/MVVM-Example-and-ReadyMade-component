package com.hyperelement.mvvmdemo.di

import com.hyperelement.mvvmdemo.data.repository.FragmentOneRepository
import com.hyperelement.mvvmdemo.data.repository.FragmentThreeRepository
import com.hyperelement.mvvmdemo.data.repository.FragmentTwoRepository
import com.hyperelement.mvvmdemo.data.repository.RxJavaExample1Repository
import com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmentone.FragmentOneViewModel
import com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmentthree.FragmentThreeViewModel
import com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmenttwo.FragmentTwoViewModel
import com.hyperelement.mvvmdemo.ui.demorxjava.RxJavaExample1VM
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //Inject ViewModels
    viewModel {
        FragmentOneViewModel(
            get()
        )
    }

    viewModel {
        FragmentTwoViewModel(
            get()
        )
    }

    viewModel {
        FragmentThreeViewModel(
            get()
        )
    }

    viewModel {
        RxJavaExample1VM(
            get()
        )
    }

    // Inject Repository
    single { FragmentOneRepository(androidContext()) }
    single { FragmentTwoRepository(androidContext()) }
    single { FragmentThreeRepository(androidContext()) }
    single { RxJavaExample1Repository(androidContext()) }

}
