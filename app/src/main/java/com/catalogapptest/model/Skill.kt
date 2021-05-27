package com.catalogapptest.model

data class Skill(
    var id: Int,
    var name: String,
    var type: String,
    var activities: List<Activity>
)
