package ru.maksonic.easypayments.data

import com.google.gson.annotations.SerializedName

/**
 * @Author maksonic on 30.11.2023
 */
data class ErrorResponse(
    @SerializedName("error_code") val code: Long,
    @SerializedName("error_msg") val errorInfo: String?
)