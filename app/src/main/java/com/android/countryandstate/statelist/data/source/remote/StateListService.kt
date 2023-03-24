package com.android.countryandstate.statelist.data.source.remote

import com.android.countryandstate.statelist.data.model.StateRequest
import com.android.countryandstate.statelist.data.model.StateResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StateListService {

    @POST("/api/v0.1/countries/states")
    fun stateList(@Body stateRequest: StateRequest): Call<StateResponse>
}