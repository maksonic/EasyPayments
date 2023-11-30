package ru.maksonic.easypayments.data.auth

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @Author maksonic on 28.11.2023
 */
data class AuthCredentials(
    @SerializedName("login") val username: String,
    @SerializedName("password") val password: String
): Serializable