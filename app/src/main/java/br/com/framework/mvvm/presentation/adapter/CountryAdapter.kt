package br.com.framework.mvvm.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.framework.mvvm.data.model.Country
import br.com.framework.mvvm.databinding.CountryItemBinding
import javax.inject.Inject

class CountryAdapter @Inject constructor(var countries: List<Country>): RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CountryAdapter.ViewHolder, position: Int) = holder.bind(countries[position])

    override fun getItemCount(): Int = countries.size


    inner class ViewHolder(private val binding: CountryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.apply {
                country.also {(name, capital) ->
                    nameTextview.text = name
                    capitalTextview.text = capital
                }
            }
        }
    }
}