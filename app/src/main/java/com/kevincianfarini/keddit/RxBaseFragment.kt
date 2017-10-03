package com.kevincianfarini.keddit

import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import rx.subscriptions.CompositeSubscription

open class RxBaseFragment : Fragment() {

    protected var subscriptions = CompositeSubscription()

    override fun onPause() {
        super.onPause()
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        subscriptions.clear()
    }

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
