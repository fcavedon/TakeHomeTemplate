package org.cavedon.takehome.network

import retrofit2.Response
import retrofit2.http.GET

interface PeopleService {

    @GET("people/")
    suspend fun getPeopleCollection(): Response<PeopleCollectionApiResponse>
}