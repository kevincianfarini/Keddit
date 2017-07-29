package com.kevincianfarini.keddit.commons.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kevincianfarini.keddit.R
import com.kevincianfarini.keddit.commons.inflate
import com.kevincianfarini.keddit.commons.interfaces.ViewType
import com.kevincianfarini.keddit.commons.interfaces.ViewTypeDelegateAdapter

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {

    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item_loading))

}
