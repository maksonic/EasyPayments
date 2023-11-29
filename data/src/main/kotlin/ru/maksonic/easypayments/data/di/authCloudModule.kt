package ru.maksonic.easypayments.data.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.maksonic.easypayments.data.ApiService
import ru.maksonic.easypayments.data.interceptors.HeaderInterceptor
import java.util.concurrent.TimeUnit

/**
 * @Author maksonic on 28.11.2023
 */
val authCloudFeatureModule = module {
    val baseUrl = "https://easypay.world/api-test/"
    val timeOut = 10L

    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(timeOut, TimeUnit.SECONDS)
        .readTimeout(timeOut, TimeUnit.SECONDS)
        .writeTimeout(timeOut, TimeUnit.SECONDS)
        .addInterceptor(HeaderInterceptor())
        .build()

    single { provideRetrofit(client = get()) }
    single { provideOkHttpClient() }
    single<ApiService> { provideRetrofit(get()).create(ApiService::class.java) }
}
