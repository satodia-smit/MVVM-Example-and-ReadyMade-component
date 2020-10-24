package com.hyperelement.mvvmdemo.di

import com.hyperelement.mvvmdemo.data.repository.*
import com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmentone.FragmentOneViewModel
import com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmentthree.FragmentThreeViewModel
import com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmenttwo.FragmentTwoViewModel
import com.hyperelement.mvvmdemo.ui.demorecyclerview.carousel.CarouselRVDemoViewModel
import com.hyperelement.mvvmdemo.ui.demorecyclerview.simple.SimpleRVDemoViewModel
import com.hyperelement.mvvmdemo.ui.demorecyclerview.simplerow.SimpleRowRVDemoViewModel
import com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.loadmore.LoadMoreVM
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
        SimpleRVDemoViewModel(
            get()
        )
    }

    viewModel {
        CarouselRVDemoViewModel(
            get()
        )
    }

    viewModel {
        SimpleRowRVDemoViewModel(
            get()
        )
    }

    viewModel {
        LoadMoreVM(
            get()
        )
    }

    // Inject Repository
    single { FragmentOneRepository(androidContext()) }
    single { FragmentTwoRepository(androidContext()) }
    single { FragmentThreeRepository(androidContext()) }
    single { SimpleRVDemoRepository(androidContext()) }
    single { CarouselRVDemoRepository(androidContext()) }
    single { SimpleRowRVDemoRepository(androidContext()) }
    single { LoadMoreRepository(androidContext()) }

}
