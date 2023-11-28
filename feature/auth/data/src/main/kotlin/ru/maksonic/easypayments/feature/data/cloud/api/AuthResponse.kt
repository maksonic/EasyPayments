package ru.maksonic.easypayments.feature.data.cloud.api

import com.google.gson.annotations.SerializedName

/**
 * @Author maksonic on 28.11.2023
 */
data class AuthResponse(
    @SerializedName("success") val isSuccess: Boolean,
    @SerializedName("response") val response: SuccessResponse,
    @SerializedName("error") val error: ErrorResponse,
)

data class SuccessResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("token") val token: String?
)

data class ErrorResponse(
    @SerializedName("error_code") val code: Long,
    @SerializedName("error_msg") val errorInfo: String?
)