package com.catalogapptest.network

import com.catalogapptest.GET_ARTICLES
import com.catalogapptest.GET_ARTICLES_DETAIL
import com.catalogapptest.network.response.ArticleDetailResponse
import com.catalogapptest.network.response.ArticlesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleService {

    @GET(GET_ARTICLES)
    fun list(
        @Header("Authorization") token: String,
        @Query("skill_id") skillId: Int,
        @Query("baby_id") babyId: Int
    ): Single<ArticlesResponse>

    @GET("$GET_ARTICLES_DETAIL/{article_id}")
    fun detail(
        @Header("Authorization") token: String,
        @Path("article_id") articleId: Long,
    ): Single<ArticleDetailResponse>

}