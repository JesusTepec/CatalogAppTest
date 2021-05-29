package com.catalogapptest.network

import com.catalogapptest.CatalogApp
import com.readystatesoftware.chuck.ChuckInterceptor
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    private const val BASE_URL = "http://staging.kinedu.com/"

    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(CatalogApp.applicationContext()))
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client.build())
            .build()
    }

    val activityService: ActivityService by lazy {
        retrofit.create(ActivityService::class.java)
    }

    val articleService: ArticleService by lazy {
        retrofit.create(ArticleService::class.java)
    }
}