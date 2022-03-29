package br.com.framework.mvvm.data.api

import br.com.framework.mvvm.data.model.Countries
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("region/europe")
    suspend fun getCuntries(): Response<Countries>
}