package com.catalogapptest.network.response

data class MetaResponse(
    var page: Int,
    var per_page: Int,
    var total_pages: Int,
    var total: Int
)
