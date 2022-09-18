package org.cavedon.takehome.ui.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.cavedon.takehome.R
import org.cavedon.takehome.dependency.DependencyManager
import org.cavedon.takehome.network.PeopleService


class PeopleViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PeopleViewModel(DependencyManager.peopleService) as T
    }
}

class PeopleViewModel(private val peopleService: PeopleService) : ViewModel() {

    private val _peopleCollectionFlow = MutableStateFlow<PeopleViewState?>(null)
    val peopleCollectionFlow: Flow<PeopleViewState?>
        get() = _peopleCollectionFlow

    fun getPeopleCollection() {
        _peopleCollectionFlow.value = PeopleViewState.Loading

        viewModelScope.launch {
            peopleService.getPeopleCollection()
                .onSuccess { successResponse ->
                    _peopleCollectionFlow.value = PeopleViewState.Success(
                        successResponse.results.map { PeopleDisplayItem(it) }
                    )
                }
                .onFailure { error ->
                    _peopleCollectionFlow.value = PeopleViewState.Error(
                        errorMessageResId = R.string.error_people_not_found,
                        additionalInfo = error.message
                    )
                }
        }
    }
}