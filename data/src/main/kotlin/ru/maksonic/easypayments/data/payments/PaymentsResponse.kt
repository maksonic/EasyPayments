package ru.maksonic.easypayments.data.payments

import com.google.gson.annotations.SerializedName
import ru.maksonic.easypayments.data.ErrorResponse

/**
 * @Author maksonic on 30.11.2023
 */
data class PaymentsResponse(
    @SerializedName("success") val isSuccess: Boolean,
    @SerializedName("response") val response: List<PaymentCloudModel>,
    @SerializedName("error") val error: ErrorResponse
)