package com.kevincianfarini.keddit.commons.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Adapter
import com.kevincianfarini.keddit.commons.RedditNewsItem
import com.kevincianfarini.keddit.commons.constants.AdapterConstants
import com.kevincianfarini.keddit.commons.interfaces.ViewType
import com.kevincianfarini.keddit.commons.interfaces.ViewTypeDelegateAdapter

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private var items: ArrayList<ViewType>
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter())
        delegateAdapters.put(AdapterConstants.TEXT_NEWS, NewsTextDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    fun addNews(news: List<RedditNewsItem>) {
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1)
    }

    fun clearAndAddNews(news: List<RedditNewsItem>) {
        this.clear()
        this.addNews(news)
    }

    fun clear() {
        var size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
        items.add(loadingItem)
        notifyItemInserted(0)
    }

    fun getNews(): List<RedditNewsItem> {
        return items
                .filter { it.getViewType() == AdapterConstants.NEWS || it.getViewType() == AdapterConstants.TEXT_NEWS }
                .map { it as RedditNewsItem }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun getItemViewType(position: Int): Int = items[position].getViewType()
}
