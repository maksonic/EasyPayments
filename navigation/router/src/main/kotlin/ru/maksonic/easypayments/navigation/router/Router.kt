package ru.maksonic.easypayments.navigation.router

import androidx.fragment.app.Fragment

/**
 * @Author maksonic on 28.11.2023
 */
interface Router {
    fun navigateFromOnboardingToAuth(fragment: Fragment)
    fun navigateFromAuthToPayments(fragment: Fragment)
    fun navigateFromPaymentsToAuth(fragment: Fragment)
    fun navigateFromPaymentsToSettings(fragment: Fragment)
    fun navigateFromSettingsToAuth(fragment: Fragment)
    fun onBack(fragment: Fragment)
}