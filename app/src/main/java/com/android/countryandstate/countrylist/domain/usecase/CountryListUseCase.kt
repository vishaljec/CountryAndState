package com.android.countryandstate.countrylist.domain.usecase

import com.android.countryandstate.countrylist.data.model.CountryResponse
import com.android.countryandstate.countrylist.domain.repository.CountryListRepository
import com.android.countryandstate.statelist.data.model.StateResponse
import com.android.countryandstate.domain.usecase.base.UseCase
import com.android.countryandstate.statelist.domain.repository.StateListRepository

class CountryListUseCase constructor(
    private val countryListRepository: CountryListRepository
) : UseCase<CountryResponse, Any?>() {

    override suspend fun run(params: Any?): CountryResponse {
        return countryListRepository.getCountryList() ?: CountryResponse(
            false,
            "Unknown error",
            emptyList()
        )
    }
}