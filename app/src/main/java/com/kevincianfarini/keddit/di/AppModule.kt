package com.kevincianfarini.keddit.di

import android.content.Context
import com.kevincianfarini.keddit.KedditApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val app: KedditApp) {

    @Singleton @Provides
    fun provideContext(): Context = app

    @Singleton @Provides
    fun provideApplication(): KedditApp = app

}