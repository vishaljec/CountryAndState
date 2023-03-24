package com.android.countryandstate.statelist.domain.usecase

import com.android.countryandstate.statelist.data.model.StateResponse
import com.android.countryandstate.domain.usecase.base.UseCase
import com.android.countryandstate.statelist.data.model.StateData
import com.android.countryandstate.statelist.domain.repository.StateListRepository

class StateListUseCase constructor(
    private val stateListRepository: StateListRepository
) : UseCase<StateResponse, Any?>() {

    override suspend fun run(params: Any?): StateResponse {
        return stateListRepository.getStateList(params.toString()) ?: StateResponse(
            false,
            "Unknown error",
            StateData("", "", emptyList())
        )
    }
}