package com.catalogapptest.network

import com.catalogapptest.network.response.TestResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/test")
    fun testRequest() : Single<List<TestResponse>>
}