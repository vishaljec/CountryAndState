package com.android.countryandstate.statelist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.countryandstate.databinding.HolderStateBinding
import com.android.countryandstate.statelist.data.model.State
import kotlin.properties.Delegates

class StateListAdapter : RecyclerView.Adapter<StateListViewHolder>() {

    var countries: List<State> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateListViewHolder {
        val binding =
            HolderStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StateListViewHolder(binding)
    }

    override fun getItemCount(): Int = if (countries.isEmpty()) 0 else countries.size

    override fun onBindViewHolder(holder: StateListViewHolder, position: Int) {
        with(holder) {
            with(countries[position]) {
                binding.tvStateName.text = this.name

            }
        }
    }
}