package com.catalogapptest.model

import com.google.gson.annotations.SerializedName

data class Article(
    var type: String,
    var id: Long,
    var name: String,
    var title: String,
    @SerializedName("min_age")
    var minAge: Int,
    @SerializedName("max_age")
    var maxAge: Int,
    var picture: String,
    @SerializedName("area_id")
    var areaId: Int,
    @SerializedName("short_description")
    var shortDescription: String,
    var body: String
)
