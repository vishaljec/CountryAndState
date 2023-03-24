package com.android.countryandstate.countrylist.domain.repository

import com.android.countryandstate.countrylist.data.model.CountryResponse

interface CountryListRepository {

    suspend fun getCountryList(): CountryResponse?
}