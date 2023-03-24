package com.android.countryandstate.statelist.domain.repository

import com.android.countryandstate.statelist.data.model.StateResponse

interface StateListRepository {

    suspend fun getStateList(country:String): StateResponse?
}