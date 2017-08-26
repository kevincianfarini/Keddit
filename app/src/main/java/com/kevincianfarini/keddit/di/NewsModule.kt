package com.kevincianfarini.keddit.di

import com.kevincianfarini.keddit.commons.api.NewsAPI
import com.kevincianfarini.keddit.commons.api.NewsRestApi
import com.kevincianfarini.keddit.commons.api.RedditApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class NewsModule {

    @Provides @Singleton
    fun provideNewsAPI(redditApi: RedditApi): NewsAPI = NewsRestApi(redditApi)

    @Provides @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApi = retrofit.create(RedditApi::class.java)
}