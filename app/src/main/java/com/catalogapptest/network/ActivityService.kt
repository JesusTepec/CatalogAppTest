package com.catalogapptest.network

import com.catalogapptest.network.response.ActivitiesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ActivityService {

    @GET("/api/v3/catalogue/activities")
    fun list(
        @Header("Authorization") token: String,
        @Query("skill_id") skillId: Int,
        @Query("baby_id") babyId: Int) : Single<ActivitiesResponse>
}