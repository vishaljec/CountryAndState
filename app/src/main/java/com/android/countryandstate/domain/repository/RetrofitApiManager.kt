package com.android.countryandstate.domain.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Call

class RetrofitApiManager {

    private val scope = CoroutineScope(Dispatchers.IO)

    suspend fun <T> call(call: Call<T>): T? {
        println("Request: ${call.request().url}, Method: ${call.request().method}")
        return scope.async {
            val result = runCatching {
                call.execute()
            }
            return@async if (result.isSuccess) {
                result.getOrNull()?.body()
            } else {
                result.getOrNull()?.body()
            }
        }.await()
    }

}
