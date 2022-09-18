package org.cavedon.takehome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import org.cavedon.takehome.databinding.FragmentResourceListBinding

abstract class ResourceListFragment<VM : ViewModel> : Fragment() {

    abstract fun provideViewModel(): VM

    internal val viewModel: VM by lazy { provideViewModel() }

    private var _binding: FragmentResourceListBinding? = null
    private val binding get() = _binding!!

    private val displayItemAdapter = DisplayItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResourceListBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = displayItemAdapter

        return binding.root
    }

    internal fun showLoadingIndicator() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    internal fun showListContent(content: List<DisplayItem>) {
        with(binding) {
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
        displayItemAdapter.submitList(content)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}