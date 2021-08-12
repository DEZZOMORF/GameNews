package com.example.gamenews.retrofit;

import com.example.gamenews.app.models.News
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET;
import retrofit2.http.Query

interface RetrofitServices  {

    @GET("/")
    fun getNewsList(@Query("page") page:Int): Observable<List<News>>
}
