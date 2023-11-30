package ru.maksonic.easypayments.data.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * @Author maksonic on 30.11.2023
 */
class GsonDoubleAdapter : JsonDeserializer<Double?> {
    override fun deserialize(js: JsonElement, t: Type?, c: JsonDeserializationContext?): Double? =
        runCatching { js.asString.toDouble() }.getOrNull()
}