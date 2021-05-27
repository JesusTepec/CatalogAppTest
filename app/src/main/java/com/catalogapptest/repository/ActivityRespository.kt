package com.catalogapptest.repository

import com.catalogapptest.network.response.ActivitiesResponse
import com.catalogapptest.network.RetrofitConfig
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ActivityRespository {

    private var activityService = RetrofitConfig.activityService

    fun getActivities(token: String): Single<ActivitiesResponse> {
        return activityService.list(token, 5, 2064732)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}