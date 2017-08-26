package com.kevincianfarini.keddit.di

import com.kevincianfarini.keddit.NewsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(
        AppModule::class,
        NewsModule::class,
        NetworkModule::class
)) interface NewsComponent {
    fun inject(newsFragment: NewsFragment)
}
