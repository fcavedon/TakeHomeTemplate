package org.cavedon.takehome.network

data class CollectionApiResponse<T>(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T>
)