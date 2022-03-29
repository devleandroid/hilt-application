package br.com.framework.mvvm.presentation.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.framework.mvvm.data.model.Country
import br.com.framework.mvvm.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel(){

    private val countryListData = MutableLiveData<List<Country>>()

    fun getCountry() = countryListData

    init {
        loadCountriesData()
    }

    private fun loadCountriesData() {
        viewModelScope.launch {
            val countries = repository.getCountries()
            when(countries.isSuccessful){
                true -> {
                    with(countries.body().orEmpty()) {
                        var countryList = listOf<Country>()
                        forEach {(_, _, _, _, _, _, capital, _, _, _, _, _, _, _, name) ->
                            if (!name.isNullOrEmpty()) {
                                countryList = countryList + Country(name, capital)
                            }
                        }
                        countryListData.postValue(countryList)
                    }
                }
                else -> {
                    Timber.e(countries.message())
                }
            }
        }
    }
}