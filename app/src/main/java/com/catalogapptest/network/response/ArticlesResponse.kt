package com.catalogapptest.network.response

import com.catalogapptest.model.Article

data class ArticlesResponse(
    var data: ArticlesResponse,
    var meta: MetaResponse,
) {
    data class ArticlesResponse(var articles: List<Article>)
}
