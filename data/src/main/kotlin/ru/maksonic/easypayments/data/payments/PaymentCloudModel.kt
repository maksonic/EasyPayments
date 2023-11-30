package ru.maksonic.easypayments.data.payments

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @Author maksonic on 30.11.2023
 */
data class PaymentCloudModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("amount") val amount: Double?,
    @SerializedName("created") val created: Long?
) : Serializable