package com.android.countryandstate.countrylist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.android.countryandstate.R
import com.android.countryandstate.countrylist.viewmodel.CountryListViewModel
import com.android.countryandstate.databinding.FragmentCountryBinding
import com.android.countryandstate.statelist.view.StateListFragment.Companion.COUNTRY
import com.android.countryandstate.utils.isNetworkAvailable
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryListFragment : Fragment() {

    private lateinit var binding: FragmentCountryBinding
    private var counteryListAdapter = CountryListAdapter(::navigateToStateList)
    private val countryViewModel: CountryListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        callApi()

        binding.rvCountry.adapter = counteryListAdapter

        with(countryViewModel) {

            countriesData.observe(this@CountryListFragment) {
                if(it.isNotEmpty()){
                    counteryListAdapter.countries = it
                    hideProgressBar()
                }else{
                    showErrorScreen("No Country Found")
                }

            }

            messageData.observe(this@CountryListFragment) {
                showErrorScreen(it)
            }

        }
        updateTitle()
        return binding.root
    }

    private fun callApi() {
        showProgressBar()
        lifecycleScope.apply {
            launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    if (requireActivity().isNetworkAvailable()) {
                        countryViewModel.getCountryList()
                    } else {
                        hideProgressBar()
                        showErrorScreen(getString(R.string.no_internet_connection))
                    }
                }
            }
        }
    }

    private fun showProgressBar() {
        binding.pbCountry.isVisible = true
        binding.rvCountry.isVisible = false
        binding.iError.lError.isVisible = false
    }

    private fun hideProgressBar() {
        binding.pbCountry.isVisible = false
        binding.rvCountry.isVisible = true
        binding.iError.lError.isVisible = false
    }

    private fun showErrorScreen(error: String) {
        binding.pbCountry.isVisible = false
        binding.rvCountry.isVisible = false
        binding.iError.apply {
            lError.isVisible = true
            tvError.text = error
        }
    }

    private fun navigateToStateList(country: String) {
        navigate(
            id = R.id.nav_action_country_list_to_state_list,
            argument = bundleOf(COUNTRY to country),
            findNavController()
        )
    }

    private fun updateTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Countries"
    }

    private fun navigate(
        id: Int,
        argument: Bundle?,
        navController: NavController
    ) {
        val destination = navController.findDestination(id)
        val action = navController.currentDestination?.getAction(id)
        if (destination != null || action != null) {
            navController.navigate(
                id,
                argument,
                null
            )
        }
    }
}