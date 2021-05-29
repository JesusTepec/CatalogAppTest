package com.catalogapptest.network.response

import com.catalogapptest.model.Article
import com.google.gson.JsonObject

data class ArticleDetailResponse(
    val data: DataArticleDetailResponse
) {
    data class DataArticleDetailResponse(
        var article: Article,
        var related_items: JsonObject
    )
}