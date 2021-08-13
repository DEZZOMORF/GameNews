package com.example.gamenews.app.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamenews.app.models.News
import com.example.gamenews.app.repository.NewsRepository
import com.example.gamenews.utils.AdapterScrollListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsViewModel: ViewModel(), AdapterScrollListener.OnLoadItemsListener {

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> get() = _newsList

    init { loadData(1) }

    private fun loadData(page: Int) {
        isLoading.value = true
        NewsRepository
            .loadData(page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { isLoading.value = false }
            .subscribe {
                _newsList.value = _newsList.value?.plus(it)?: it
                isLoading.value = false
            }
    }

    //Pagination
    override val isLoading: MutableLiveData<Boolean>
        get() = MutableLiveData(false)

    override fun onAdapterScrolled(offset: Int){
        loadData(offset)
    }

}