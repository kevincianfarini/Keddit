package com.kevincianfarini.keddit.commons

import com.kevincianfarini.keddit.commons.constants.AdapterConstants
import com.kevincianfarini.keddit.commons.interfaces.ViewType

data class RedditNews(
        val after: String,
        val before: String,
        val news: List<RedditNewsItem>
)

data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
) : ViewType {
    override fun getViewType(): Int = AdapterConstants.NEWS
}
