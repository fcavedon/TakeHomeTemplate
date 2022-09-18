package org.cavedon.takehome.repository

import org.cavedon.takehome.network.PeopleCollectionApiResponse
import org.cavedon.takehome.network.PeopleService
import retrofit2.Response

class PeopleRepository(private val peopleService: PeopleService) {

    suspend fun getPeopleCollection(): PeopleRepositoryResponse {
        val apiResponse = peopleService.getPeopleCollection()

        return when {
            apiResponse.isSuccessful -> PeopleRepositoryResponse.Success(
                apiResponse.toPeopleCollectionResponse().results
            )
            else -> apiResponse.toErrorResponse()
        }
    }

    private fun <T> Response<T>.toPeopleCollectionResponse() = body() as PeopleCollectionApiResponse

    private fun <T> Response<T>.toErrorResponse() = PeopleRepositoryResponse.Error(
        errorCode = code(),
        errorMessage = errorBody()?.string()
    )
}
