package com.catalogapptest.repository

import com.catalogapptest.network.response.ActivitiesResponse
import com.catalogapptest.network.RetrofitConfig
import com.catalogapptest.network.response.ActivityDetailResponse
import com.catalogapptest.network.response.ArticleDetailResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ActivityRespository @Inject constructor() {

    //todo: provide with dagger
    private var activityService = RetrofitConfig.activityService

    fun getActivities(token: String, skillId: Int, babyId: Int): Single<ActivitiesResponse> {
        return activityService.list(token, skillId, babyId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetails(token: String, activityId: Long, babyId: Int, locale: String): Single<ActivityDetailResponse> {
        return activityService.detail(token, activityId, babyId, locale)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}