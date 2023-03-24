package com.android.countryandstate.countrylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.countryandstate.countrylist.data.model.Country
import com.android.countryandstate.countrylist.data.model.CountryResponse
import com.android.countryandstate.countrylist.domain.usecase.CountryListUseCase
import com.android.countryandstate.domain.model.ApiError
import com.android.countryandstate.domain.usecase.base.UseCaseResponse
import kotlinx.coroutines.cancel

class CountryListViewModel constructor(private val countryListUseCase: CountryListUseCase) :
    ViewModel() {

    val countriesData = MutableLiveData<List<Country>>()
    private val countries = ArrayList<Country>()
    val messageData = MutableLiveData<String>()
    fun getCountryList() {
        countryListUseCase.invoke(
            viewModelScope, null,
            object : UseCaseResponse<CountryResponse> {
                override fun onSuccess(countryRes: CountryResponse) {
                    if (!countryRes.error) {
                        countries.addAll(countryRes.data)
                        countriesData.value = countries
                    } else {
                        messageData.value = countryRes.msg
                    }
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                }
            }
        )
    }


    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}