package com.catalogapptest.network

import com.catalogapptest.GET_ACTIVITIES
import com.catalogapptest.GET_ACTIVITIES_DETAIL
import com.catalogapptest.GET_ARTICLES
import com.catalogapptest.GET_ARTICLES_DETAIL
import com.catalogapptest.network.response.ActivitiesResponse
import com.catalogapptest.network.response.ActivityDetailResponse
import com.catalogapptest.network.response.ArticleDetailResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ActivityService {

    /**
     * endpoint GET for list of activities
     */
    @GET(GET_ACTIVITIES)
    fun list(
        @Header("Authorization") token: String,
        @Query("skill_id") skillId: Int,
        @Query("baby_id") babyId: Int
    ): Single<ActivitiesResponse>

    @GET("$GET_ACTIVITIES_DETAIL/{activity_id}")
    fun detail(
        @Header("Authorization") token: String,
        @Path("activity_id") articleId: Long,
        @Query("baby_id") babyId: Int,
        @Query("locale") locale: String
    ): Single<ActivityDetailResponse>

}