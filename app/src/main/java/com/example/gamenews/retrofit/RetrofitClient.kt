package com.example.gamenews.retrofit

import com.example.gamenews.BuildConfig
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun create(): RetrofitServices {
        val OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(provideLoggingInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://188.40.167.45:3001")
            .client(OkHttpClient)
            .build()

        return retrofit.create(RetrofitServices::class.java);
    }

    private fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
}