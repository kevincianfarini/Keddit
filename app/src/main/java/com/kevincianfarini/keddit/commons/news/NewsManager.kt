package com.kevincianfarini.keddit.commons.news

import com.kevincianfarini.keddit.commons.RedditNews
import com.kevincianfarini.keddit.commons.RedditNewsItem
import com.kevincianfarini.keddit.commons.api.RestApi
import rx.Observable


class NewsManager(private val api: RestApi = RestApi()) {

    fun getNews(after: String = "", limit: String = "10"): Observable<RedditNews> {
        return Observable.create {
            subscriber ->

            val callResponse = api.getNews(after, limit)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val data = response.body().data

                val news = data.children.map {
                    val item = it.data
                    RedditNewsItem(
                            item.author,
                            item.title,
                            item.num_comments,
                            item.created,
                            item.thumbnail,
                            item.url
                    )
                }

                val redditNews = RedditNews(
                        data.after ?: "",
                        data.before ?: "",
                        news
                )

                subscriber.onNext(redditNews)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

}