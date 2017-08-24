package com.kevincianfarini.keddit

import com.kevincianfarini.keddit.commons.RedditNews
import com.kevincianfarini.keddit.commons.api.NewsAPI
import com.kevincianfarini.keddit.commons.api.RedditDataResponse
import com.kevincianfarini.keddit.commons.api.RedditNewsResponse
import com.kevincianfarini.keddit.commons.mock
import com.kevincianfarini.keddit.commons.news.NewsManager
import junit.framework.TestCase
import retrofit2.Call
import rx.observers.TestSubscriber
import org.mockito.Mockito.*
import retrofit2.Response

class NewsManagerTest : TestCase() {

    lateinit var testSub: TestSubscriber<RedditNews>
    lateinit var apiMock: NewsAPI
    lateinit var callMock: Call<RedditNewsResponse>

    override fun setUp() {
        testSub = TestSubscriber<RedditNews>()
        apiMock = mock<NewsAPI>()
        callMock = mock<Call<RedditNewsResponse>>()
        `when`(apiMock.getNews(anyString(), anyString())).thenReturn(callMock)
    }

    fun testSuccessBasic() {
        val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
        val response = Response.success(redditNewsResponse)

        `when`(callMock.execute()).thenReturn(response)

        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertCompleted()
    }

}