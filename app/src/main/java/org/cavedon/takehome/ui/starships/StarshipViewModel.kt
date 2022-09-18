package org.cavedon.takehome.ui.starships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.cavedon.takehome.R
import org.cavedon.takehome.dependency.DependencyManager
import org.cavedon.takehome.network.StarshipService


class StarshipViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StarshipViewModel(DependencyManager.starshipService) as T
    }
}

class StarshipViewModel(private val starshipService: StarshipService) : ViewModel() {

    private val _starshipCollectionFlow = MutableStateFlow<StarshipViewState?>(null)
    val starshipCollectionFlow: Flow<StarshipViewState?>
        get() = _starshipCollectionFlow

    fun getStarshipCollection() {
        _starshipCollectionFlow.value = StarshipViewState.Loading

        viewModelScope.launch {
            starshipService.getStarshipCollection()
                .onSuccess { successResponse ->
                    _starshipCollectionFlow.value = StarshipViewState.Success(
                        successResponse.results.map { StarshipDisplayItem(it) }
                    )
                }
                .onFailure { error ->
                    _starshipCollectionFlow.value = StarshipViewState.Error(
                        errorMessageResId = R.string.error_starship_not_found,
                        additionalInfo = error.message
                    )
                }
        }
    }
}