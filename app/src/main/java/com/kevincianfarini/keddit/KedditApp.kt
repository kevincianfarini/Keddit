package com.kevincianfarini.keddit

import android.app.Application
import com.kevincianfarini.keddit.di.AppModule
import com.kevincianfarini.keddit.di.DaggerNewsComponent
import com.kevincianfarini.keddit.di.NewsComponent


class KedditApp : Application() {

    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}