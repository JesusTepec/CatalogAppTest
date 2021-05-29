package com.catalogapptest.network.response

import com.catalogapptest.model.Activity
import com.catalogapptest.model.Article
import com.google.gson.JsonObject

data class ActivityDetailResponse(
    val data: DataActivityDetailResponse
) {
    data class DataActivityDetailResponse(
        var activity: Activity
    )
}