package com.android.countryandstate.statelist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.android.countryandstate.R
import com.android.countryandstate.databinding.FragmentStateBinding
import com.android.countryandstate.statelist.viewmodel.StateListViewModel
import com.android.countryandstate.utils.isNetworkAvailable
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class StateListFragment : Fragment() {

    private lateinit var binding: FragmentStateBinding
    private var stateListAdapter = StateListAdapter()
    private val stateListViewModel: StateListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStateBinding.inflate(inflater, container, false)
        callApi()
        binding.rvState.adapter = stateListAdapter
        with(stateListViewModel) {

            countriesData.observe(this@StateListFragment) {
                if (it.isNotEmpty()) {
                    stateListAdapter.countries = it
                    hideProgressBar()
                } else {
                    showErrorScreen("No State Found in ${country()}")
                }

            }

            messageData.observe(this@StateListFragment) {
                showErrorScreen(it)
            }

        }
        updateTitle()
        return binding.root
    }


    companion object {
        const val COUNTRY = "country"
    }

    private fun updateTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.let {
            it.title = "Sates of ${country()}"
        }

    }

    private fun showProgressBar() {
        binding.pbState.isVisible = true
        binding.rvState.isVisible = false
        binding.iError.lError.isVisible = false
    }

    private fun hideProgressBar() {
        binding.pbState.isVisible = false
        binding.rvState.isVisible = true
        binding.iError.lError.isVisible = false
    }

    private fun callApi() {
        showProgressBar()
        lifecycleScope.apply {
            launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    if (requireActivity().isNetworkAvailable()) {
                        stateListViewModel.getStateList(country())
                    } else {
                        hideProgressBar()
                        Toast.makeText(
                            requireActivity(),
                            getString(R.string.no_internet_connection),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun showErrorScreen(error: String) {
        binding.pbState.isVisible = false
        binding.rvState.isVisible = false
        binding.iError.apply {
            lError.isVisible = true
            tvError.text = error
        }
    }

    private fun country() = arguments?.getString(COUNTRY) ?: ""
}