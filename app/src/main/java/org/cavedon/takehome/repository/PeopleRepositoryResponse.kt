package org.cavedon.takehome.repository

import org.cavedon.takehome.model.People

sealed class PeopleRepositoryResponse {
    data class Success(val peopleCollection: List<People>) : PeopleRepositoryResponse()
    data class Error(val errorCode: Int, val errorMessage: String? = null) :
        PeopleRepositoryResponse()
}