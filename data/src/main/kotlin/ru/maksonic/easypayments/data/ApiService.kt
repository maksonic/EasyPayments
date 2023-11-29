package ru.maksonic.easypayments.data

import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @Author maksonic on 28.11.2023
 */
interface ApiService {
    @POST("login")
    suspend fun authWithUsernameAndPassword(@Body credentials: AuthCredentials): AuthResponse
}