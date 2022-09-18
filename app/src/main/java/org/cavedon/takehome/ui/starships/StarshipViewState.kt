package org.cavedon.takehome.ui.starships

import androidx.annotation.StringRes

sealed class StarshipViewState {

    object Loading : StarshipViewState()

    data class Error(
        @StringRes val errorMessageResId: Int,
        val additionalInfo: String? = null
    ) : StarshipViewState()

    data class Success(val starshipList: List<StarshipDisplayItem>) : StarshipViewState()
}