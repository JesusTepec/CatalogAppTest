package com.catalogapptest.network.response

import com.catalogapptest.model.Skill

data class ActivitiesResponse(
    var data: Skill,
    var meta: MetaResponse
)
