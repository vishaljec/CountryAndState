package com.android.countryandstate.statelist.data.repository

import com.android.countryandstate.statelist.data.model.StateResponse
import com.android.countryandstate.domain.repository.RetrofitApiManager
import com.android.countryandstate.statelist.data.model.StateRequest
import com.android.countryandstate.statelist.data.source.remote.StateListService
import com.android.countryandstate.statelist.domain.repository.StateListRepository

class StateListRepositoryImp(
    private val apiService: StateListService, private val retrofitApiManager: RetrofitApiManager
) : StateListRepository {

    override suspend fun getStateList(country: String): StateResponse? {
        val request = apiService.stateList(StateRequest(country))
        return retrofitApiManager.call(request)
    }
}