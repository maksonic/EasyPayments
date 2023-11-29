package ru.maksonic.easypayments.feature.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.maksonic.easypayments.common.ui.BaseScreen
import ru.maksonic.easypayments.feature.settings.databinding.ScreenSettingsBinding

/**
 * @Author maksonic on 29.11.2023
 */
class SettingsScreen : BaseScreen<ScreenSettingsBinding, Any, Any>() {
    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> ScreenSettingsBinding
        get() = ScreenSettingsBinding::inflate

    override fun render(savedInstanceState: Bundle?) {

    }
}