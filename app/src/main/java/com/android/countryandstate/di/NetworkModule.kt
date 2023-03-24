package com.android.countryandstate.di

import com.android.countryandstate.countrylist.data.repository.CountryListRepositoryImp
import com.android.countryandstate.countrylist.data.source.remote.CountryListService
import com.android.countryandstate.countrylist.domain.repository.CountryListRepository
import com.android.countryandstate.countrylist.domain.usecase.CountryListUseCase
import com.android.countryandstate.domain.repository.RetrofitApiManager
import com.android.countryandstate.statelist.data.repository.StateListRepositoryImp
import com.android.countryandstate.statelist.data.source.remote.StateListService
import com.android.countryandstate.statelist.domain.repository.StateListRepository
import com.android.countryandstate.statelist.domain.usecase.StateListUseCase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

private const val BASE_URL = "https://countriesnow.space/"

val NetworkModule = module {

    single { createCountryListService(get()) }

    single { createStateListService(get()) }

    single { createRetrofit(get(), BASE_URL) }

    single { createOkHttpClient() }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder().baseUrl(url).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createCountryListService(retrofit: Retrofit): CountryListService {
    return retrofit.create(CountryListService::class.java)
}

fun createStateListService(retrofit: Retrofit): StateListService {
    return retrofit.create(StateListService::class.java)
}

fun createCountryRepository(
    apiService: CountryListService,
    retrofitApiManager: RetrofitApiManager
): CountryListRepository {
    return CountryListRepositoryImp(apiService, retrofitApiManager)
}

fun createStateListRepository(
    apiService: StateListService,
    retrofitApiManager: RetrofitApiManager
): StateListRepository {
    return StateListRepositoryImp(apiService, retrofitApiManager)
}


fun createRetrofitManager() = RetrofitApiManager()

fun createCountryUseCase(countryRepository: CountryListRepository): CountryListUseCase {
    return CountryListUseCase(countryRepository)
}

fun createStateListUseCase(stateListRepository: StateListRepository): StateListUseCase {
    return StateListUseCase(stateListRepository)
}
