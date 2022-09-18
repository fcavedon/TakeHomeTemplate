package org.cavedon.takehome.network

import org.cavedon.takehome.model.Starship
import retrofit2.http.GET

interface StarshipService {

    @GET("starships/")
    suspend fun getStarshipCollection(): Result<CollectionApiResponse<Starship>>

}