package com.hyperelement.mvvmdemo.ui.demorecyclerview.carousel

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.carousel
import com.hyperelement.mvvmdemo.RecentlyActiveItemBindingModel_
import com.hyperelement.mvvmdemo.headerItem
import com.hyperelement.mvvmdemo.messageItem
import com.hyperelement.mvvmdemo.ui.demorecyclerview.carousel.data.Message
import com.hyperelement.mvvmdemo.ui.demorecyclerview.carousel.data.Profile

class CarouselRVController : AsyncEpoxyController() {

    var recentlyActive: List<Profile> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }
    var allMessages: List<Message> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        headerItem {
            id("recently_active")
            title("Recently Active")
        }
        val models = recentlyActive.map {
            RecentlyActiveItemBindingModel_()
                .id(it.id)
                .profile(it)
        }
        carousel {
            id("recent")
            padding(Carousel.Padding.dp(0, 4, 0, 16, 8))
            models(models)
        }



        headerItem {
            id("all_messages")
            title("All Messages")
        }
        allMessages.forEach {
            messageItem {
                id(it.id)
                message(it)
            }
        }
    }
}