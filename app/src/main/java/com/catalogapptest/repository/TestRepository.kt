package com.catalogapptest.repository

import com.catalogapptest.model.TestResponse
import com.catalogapptest.network.ApiService
import com.catalogapptest.network.RetrofitConfig
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers;

class TestRepository {

    private var apiService: ApiService = RetrofitConfig.apiService

    fun test() : Single<List<TestResponse>> {
        return apiService.testRequest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}