package com.android.countryandstate.countrylist.data.source.remote

import com.android.countryandstate.countrylist.data.model.CountryResponse
import com.android.countryandstate.statelist.data.model.StateResponse
import retrofit2.Call
import retrofit2.http.GET

interface CountryListService {

    @GET("/api/v0.1/countries/flag/unicode")
    fun countryList(): Call<CountryResponse>
}