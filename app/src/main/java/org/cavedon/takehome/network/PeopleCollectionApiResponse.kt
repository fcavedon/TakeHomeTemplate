package org.cavedon.takehome.network

import org.cavedon.takehome.model.People

data class PeopleCollectionApiResponse(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<People>
)