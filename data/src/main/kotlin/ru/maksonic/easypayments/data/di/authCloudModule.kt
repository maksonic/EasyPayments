package ru.maksonic.easypayments.data.di

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.maksonic.easypayments.data.ApiService
import ru.maksonic.easypayments.data.interceptors.HeaderInterceptor
import ru.maksonic.easypayments.data.interceptors.PaymentsInterceptor
import ru.maksonic.easypayments.data.utils.GsonDoubleAdapter
import java.util.concurrent.TimeUnit

/**
 * @Author maksonic on 28.11.2023
 */
val authCloudFeatureModule = module {
    val baseUrl = "https://easypay.world/api-test/"
    val timeOut = 10L

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .registerTypeAdapter(Double::class.javaObjectType, GsonDoubleAdapter())
            .create()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .build()
    }

    fun provideOkHttpClient(
        paymentsInterceptor: PaymentsInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(timeOut, TimeUnit.SECONDS)
        .readTimeout(timeOut, TimeUnit.SECONDS)
        .writeTimeout(timeOut, TimeUnit.SECONDS)
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(paymentsInterceptor)
        .build()

    single { provideRetrofit(client = get()) }
    single { PaymentsInterceptor() }
    single { provideOkHttpClient(paymentsInterceptor = get()) }
    single<ApiService> { provideRetrofit(get()).create(ApiService::class.java) }
}