package com.kevincianfarini.keddit

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevincianfarini.keddit.commons.RedditNews
import com.kevincianfarini.keddit.commons.adapters.InfiniteScrollListener
import com.kevincianfarini.keddit.commons.adapters.NewsAdapter
import com.kevincianfarini.keddit.commons.inflate
import com.kevincianfarini.keddit.commons.news.NewsManager
import kotlinx.android.synthetic.main.news_fragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NewsFragment : RxBaseFragment() {

    private val newsList: RecyclerView by lazy {
        news_list
    }

    private var redditNews: RedditNews? = null

    private val newsManager: NewsManager by lazy { NewsManager() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            redditNews = retrievedNews
                            (newsList.adapter as NewsAdapter).addNews(retrievedNews.news)
                        },
                        { e -> Snackbar.make(newsList, e.message ?: "", Snackbar.LENGTH_LONG).show() }
                )
        subscriptions.add(subscription)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newsList.setHasFixedSize(true)

        initAdapter()

        val layoutManager = LinearLayoutManager(context)

        newsList.layoutManager = layoutManager
        newsList.clearOnScrollListeners()
        newsList.addOnScrollListener(InfiniteScrollListener(this::requestNews, layoutManager))

        if (savedInstanceState == null) {
            requestNews()
        }
    }

    private fun initAdapter() {
        if (newsList.adapter == null) {
            newsList.adapter = NewsAdapter()
        }
    }
}