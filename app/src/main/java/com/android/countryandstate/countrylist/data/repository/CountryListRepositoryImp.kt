package com.android.countryandstate.countrylist.data.repository

import com.android.countryandstate.countrylist.data.model.CountryResponse
import com.android.countryandstate.countrylist.data.source.remote.CountryListService
import com.android.countryandstate.countrylist.domain.repository.CountryListRepository
import com.android.countryandstate.domain.repository.RetrofitApiManager

class CountryListRepositoryImp(
    private val apiService: CountryListService, private val retrofitApiManager: RetrofitApiManager
) : CountryListRepository {

    override suspend fun getCountryList(): CountryResponse? {
        val request = apiService.countryList()
        return retrofitApiManager.call(request)
    }
}