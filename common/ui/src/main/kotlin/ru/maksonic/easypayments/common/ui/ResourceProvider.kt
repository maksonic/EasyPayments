package ru.maksonic.easypayments.common.ui

import androidx.annotation.StringRes

/**
 * @Author maksonic on 28.11.2023
 */
interface ResourceProvider {
    fun getString(@StringRes id: Int, vararg formatArgs: Any?): String
}
