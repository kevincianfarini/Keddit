package com.kevincianfarini.keddit.commons.api

/**
 * Created by kevin on 8/20/17.
 */

import retrofit2.Call

interface NewsAPI {
    fun getNews(after: String, limit: String): Call<RedditNewsResponse>
}