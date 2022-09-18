package org.cavedon.takehome.network

import org.cavedon.takehome.model.People
import retrofit2.http.GET

interface PeopleService {

    @GET("people/")
    suspend fun getPeopleCollection(): Result<CollectionApiResponse<People>>
}