package ru.maksonic.easypayments.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import org.koin.java.KoinJavaComponent.inject
import ru.maksonic.easypayments.feature.domain.AuthRepository

/**
 * @Author maksonic on 30.11.2023
 */
class PaymentsInterceptor : Interceptor {

    private val authRepository: AuthRepository by inject(AuthRepository::class.java)
    private var token = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        authRepository.getToken()
            .onSuccess { token = it }
            .onFailure { token = "" }

        val request = chain.request().newBuilder().addHeader("token", token).build()

        return chain.proceed(request)
    }
}
