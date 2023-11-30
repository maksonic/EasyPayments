package ru.maksonic.easypayments.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.maksonic.easypayments.data.auth.AuthCredentials
import ru.maksonic.easypayments.data.auth.AuthResponse
import ru.maksonic.easypayments.data.payments.PaymentsResponse

/**
 * @Author maksonic on 28.11.2023
 */
interface ApiService {
    @POST("login")
    suspend fun authWithUsernameAndPassword(@Body credentials: AuthCredentials): AuthResponse

    @GET("payments")
    suspend fun getPayments(): PaymentsResponse
}