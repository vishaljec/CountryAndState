package com.android.countryandstate.domain.usecase.base

import com.android.countryandstate.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}

