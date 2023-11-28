package ru.maksonic.easypayments.navigation.graph

import androidx.fragment.app.Fragment
import ru.maksonic.easypayments.navigation.router.Router

/**
 * @Author maksonic on 28.11.2023
 */

class AppNavigator : AbstractNavigator(), Router {

    override fun navigateFromOnboardingToAuth(fragment: Fragment) =
        fragment.navigate(actionId = R.id.action_onboardingScreen_to_authScreen)

    override fun onBack(fragment: Fragment): Unit = fragment.requireActivity().onBackPressed()
}

