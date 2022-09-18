package org.cavedon.takehome.dependency

import org.cavedon.takehome.network.PeopleService
import org.cavedon.takehome.repository.PeopleRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyManager {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val peopleService by lazy { retrofit.create(PeopleService::class.java) }

    val peopleRepository = PeopleRepository(peopleService)
}