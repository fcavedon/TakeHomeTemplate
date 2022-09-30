package org.cavedon.takehome.dependency

import org.cavedon.takehome.network.PeopleService
import org.cavedon.takehome.network.StarshipService
import org.cavedon.takehome.network.calladapter.ResultCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyManager {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    val peopleService: PeopleService by lazy { retrofit.create(PeopleService::class.java) }
    val starshipService: StarshipService by lazy { retrofit.create(StarshipService::class.java) }
}