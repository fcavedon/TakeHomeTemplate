package org.cavedon.takehome.ui.people

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.cavedon.takehome.ui.ResourceListFragment

class PeopleFragment : ResourceListFragment<PeopleViewModel>() {

    override fun provideViewModel(): PeopleViewModel {
        return ViewModelProvider(this, PeopleViewModelFactory()).get(PeopleViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPeopleCollection()

        viewModel.peopleCollectionFlow.filterNotNull().onEach { viewState ->
            when (viewState) {
                is PeopleViewState.Loading -> showLoadingIndicator()
                is PeopleViewState.Success -> showListContent(viewState.peopleList)
                is PeopleViewState.Error -> println("boooo!!! $viewState")
            }
        }.launchIn(lifecycleScope)
    }
}
