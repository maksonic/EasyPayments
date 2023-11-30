package ru.maksonic.easypayments.data.auth

import com.google.gson.annotations.SerializedName
import ru.maksonic.easypayments.data.ErrorResponse

/**
 * @Author maksonic on 28.11.2023
 */
data class AuthResponse(
    @SerializedName("success") val isSuccess: Boolean,
    @SerializedName("response") val response: SuccessAuthResponse,
    @SerializedName("error") val error: ErrorResponse,
)

data class SuccessAuthResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("token") val token: String?
)