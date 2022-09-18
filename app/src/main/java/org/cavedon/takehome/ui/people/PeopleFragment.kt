package org.cavedon.takehome.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.cavedon.takehome.databinding.FragmentPeopleBinding
import org.cavedon.takehome.ui.DisplayItemAdapter

class PeopleFragment : Fragment() {

    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    private val displayItemAdapter = DisplayItemAdapter()

    private val viewModel: PeopleViewModel by lazy {
        ViewModelProvider(this, PeopleViewModelFactory()).get(PeopleViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)

        binding.peopleRecyclerView.adapter = displayItemAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPeopleCollection()

        viewModel.peopleCollectionFlow.filterNotNull().onEach { viewState ->
            when (viewState) {
                is PeopleViewState.Loading -> showLoadingIndicator()
                is PeopleViewState.Success -> showPeopleList(viewState.peopleList)
                is PeopleViewState.Error -> println("boooo!!! $viewState")
            }
        }.launchIn(lifecycleScope)
    }

    private fun showLoadingIndicator() {
        with(binding) {
            peopleProgressBar.visibility = View.VISIBLE
            peopleRecyclerView.visibility = View.GONE
        }
    }

    private fun showPeopleList(peopleList: List<PeopleDisplayItem>) {
        with(binding) {
            peopleProgressBar.visibility = View.GONE
            peopleRecyclerView.visibility = View.VISIBLE
        }
        displayItemAdapter.submitList(peopleList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
