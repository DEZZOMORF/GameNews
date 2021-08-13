package com.example.gamenews.utils

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class AdapterScrollListener( private val listener: OnLoadItemsListener) : RecyclerView.OnScrollListener() {

    private var logsOffset: Int = 1
    private var lastFirstVisibleLogsItem: Int = 0

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val currentFirstVisibleItem =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        if (isLogsLastItemVisible(recyclerView) && listener.isLoading.value != true) {
            logsOffset++
            listener.onAdapterScrolled(logsOffset)
        }
        lastFirstVisibleLogsItem = currentFirstVisibleItem
    }

    private fun isLogsLastItemVisible(recyclerView: RecyclerView): Boolean {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val lastPositionVisible = layoutManager.findLastCompletelyVisibleItemPosition()
        val itemCount = recyclerView.adapter?.itemCount ?: 0
        return lastPositionVisible + 1 >= itemCount
    }

    interface OnLoadItemsListener {
        val isLoading: LiveData<Boolean>
        fun onAdapterScrolled(offset: Int)
    }
}