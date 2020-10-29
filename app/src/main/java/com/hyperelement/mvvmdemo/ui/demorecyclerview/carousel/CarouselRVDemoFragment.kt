package com.hyperelement.mvvmdemo.ui.demorecyclerview.carousel

import android.os.Bundle
import android.view.View
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.ui.demorecyclerview.carousel.data.Data
import kotlinx.android.synthetic.main.activity_main_one.*

class CarouselRVDemoFragment :
    BaseFragment<CarouselRVDemoViewModel>(
        R.layout.fragment_carousel_rv,
        CarouselRVDemoViewModel::class
    ) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = CarouselRVController()
        messagesView.setController(controller)

        controller.allMessages = Data.messages
        controller.recentlyActive = Data.recentlyActive

    }
}