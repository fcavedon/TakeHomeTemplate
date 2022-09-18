package org.cavedon.takehome.ui.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.cavedon.takehome.R
import org.cavedon.takehome.dependency.DependencyManager
import org.cavedon.takehome.repository.PeopleRepository
import org.cavedon.takehome.repository.PeopleRepositoryResponse


class PeopleViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PeopleViewModel(DependencyManager.peopleRepository) as T
    }
}

class PeopleViewModel(private val peopleRepository: PeopleRepository) : ViewModel() {

    private val _peopleCollectionFlow = MutableStateFlow<PeopleViewState?>(null)
    val peopleCollectionFlow: Flow<PeopleViewState?>
        get() = _peopleCollectionFlow

    fun getPeopleCollection() {
        _peopleCollectionFlow.value = PeopleViewState.Loading

        viewModelScope.launch {
            _peopleCollectionFlow.value =
                when (val repositoryResponse = peopleRepository.getPeopleCollection()) {
                    is PeopleRepositoryResponse.Success -> PeopleViewState.Success(
                        repositoryResponse.peopleCollection.map { PeopleDisplayItem(it) }
                    )
                    is PeopleRepositoryResponse.Error -> PeopleViewState.Error(
                        errorMessageResId = R.string.error_people_not_found,
                        additionalInfo = repositoryResponse.errorMessage
                    )
                }
        }
    }
}