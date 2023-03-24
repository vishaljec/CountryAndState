package com.android.countryandstate.statelist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.countryandstate.domain.model.ApiError
import com.android.countryandstate.domain.usecase.base.UseCaseResponse
import com.android.countryandstate.statelist.data.model.State
import com.android.countryandstate.statelist.data.model.StateResponse
import com.android.countryandstate.statelist.domain.usecase.StateListUseCase
import kotlinx.coroutines.cancel

class StateListViewModel constructor(private val stateListUseCase: StateListUseCase) : ViewModel() {

    val countriesData = MutableLiveData<List<State>>()
    private val countries = ArrayList<State>()
    val messageData = MutableLiveData<String>()
    fun getStateList(country: String) {
        stateListUseCase.invoke(
            viewModelScope, country,
            object : UseCaseResponse<StateResponse> {
                override fun onSuccess(countryRes: StateResponse) {
                    if (!countryRes.error) {
                        countries.addAll(countryRes.data.states)
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