package com.android.countryandstate.countrylist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.countryandstate.countrylist.data.model.Country
import com.android.countryandstate.databinding.HolderCountryBinding
import kotlin.properties.Delegates

class CountryListAdapter(private val onSelect: (String) -> Unit) :
    RecyclerView.Adapter<CountryListViewHolder>() {

    var countries: List<Country> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val binding =
            HolderCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryListViewHolder(binding)
    }

    override fun getItemCount(): Int = if (countries.isEmpty()) 0 else countries.size

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        with(holder) {
            with(countries[position]) {
                val country = this.name + " " + this.unicodeFlag
                binding.tvCountryName.text = country
                binding.rCountry.setOnClickListener {
                    onSelect(this.name)
                }
            }

        }
    }
}