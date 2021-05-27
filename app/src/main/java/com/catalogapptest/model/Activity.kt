package com.catalogapptest.model

import com.google.gson.annotations.SerializedName

data class Activity(
    var name: String,
    var age: Int,
    @SerializedName("age_group")
    var ageGroup: String,
    @SerializedName("activity_type")
    var activityType: String,
    @SerializedName("is_holiday")
    var isHoliday: Boolean,
    var domainId: Int,
    var areaId: Int,
    @SerializedName("min_age")
    var minAge: Int,
    var purpose: String,
    var thumbnail: String,
    @SerializedName("active_milestones")
    var activeMilestones: Int,
    @SerializedName("completed_milestones")
    var completedMilestones: Int,
    var locked: Boolean,
    @SerializedName("dap_lifes_checked")
    var dapLifesChecked: Boolean
)
