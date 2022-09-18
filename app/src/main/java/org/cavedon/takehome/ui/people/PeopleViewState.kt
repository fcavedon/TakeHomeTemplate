package org.cavedon.takehome.ui.people

import androidx.annotation.StringRes

sealed class PeopleViewState {

    object Loading : PeopleViewState()

    data class Error(
        @StringRes val errorMessageResId: Int,
        val additionalInfo: String? = null
    ) : PeopleViewState()

    data class Success(val peopleList: List<PeopleDisplayItem>) : PeopleViewState()
}