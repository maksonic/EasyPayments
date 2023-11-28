package ru.maksonic.easypayments

import android.content.Context
import ru.maksonic.easypayments.common.ui.ResourceProvider

/**
 * @Author maksonic on 28.11.2023
 */
class ResourceProviderCore (private val context: Context) : ResourceProvider {
    override fun getString(id: Int, vararg formatArgs: Any?) =
        context.getString(id, *formatArgs)
}