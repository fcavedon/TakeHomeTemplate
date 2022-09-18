package org.cavedon.takehome.ui.starships

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.cavedon.takehome.ui.ResourceListFragment

class StarshipFragment : ResourceListFragment<StarshipViewModel>() {

    override fun provideViewModel(): StarshipViewModel {
        return ViewModelProvider(
            this,
            StarshipViewModelFactory()
        ).get(StarshipViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStarshipCollection()

        viewModel.starshipCollectionFlow.filterNotNull().onEach { viewState ->
            when (viewState) {
                is StarshipViewState.Loading -> showLoadingIndicator()
                is StarshipViewState.Success -> showListContent(viewState.starshipList)
                is StarshipViewState.Error -> println("boooo!!! $viewState")
            }
        }.launchIn(lifecycleScope)
    }
}
