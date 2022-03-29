package br.com.framework.mvvm.data.repository

import br.com.framework.mvvm.data.api.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getCountries() = apiService.getCuntries()
}