package com.example.gamenews.app.repository

import com.example.gamenews.app.models.News
import com.example.gamenews.retrofit.RetrofitClient
import io.reactivex.rxjava3.core.Observable

object NewsRepository {

    fun loadData(page: Int) : Observable<List<News>> {
        val apiService = RetrofitClient.create()
        return apiService.getNewsList(page)
    }

}