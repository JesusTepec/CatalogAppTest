package com.catalogapptest.repository

import com.catalogapptest.network.RetrofitConfig
import com.catalogapptest.network.response.ArticleDetailResponse
import com.catalogapptest.network.response.ArticlesResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ArticleRepository @Inject constructor() {

    //Todo: provide with dagger
    private var articleService = RetrofitConfig.articleService

    fun getArticles(token: String, skillId: Int, babyId: Int): Single<ArticlesResponse> {
        return articleService.list(token, skillId, babyId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetails(token: String, articleId: Long): Single<ArticleDetailResponse> {
        return articleService.detail(token, articleId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}