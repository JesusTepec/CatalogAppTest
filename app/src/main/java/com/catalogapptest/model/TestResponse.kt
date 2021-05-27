package com.catalogapptest.model

import com.google.gson.annotations.SerializedName

data class TestResponse(

    @SerializedName("id_operation")
    val idOperation: String,
    val message: String
)
