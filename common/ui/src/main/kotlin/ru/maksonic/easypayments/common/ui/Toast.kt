package ru.maksonic.easypayments.common.ui

import android.content.Context
import android.widget.Toast

/**
 * @Author maksonic on 28.11.2023
 */
fun Context.toastShortTime(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toastLongTime(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}