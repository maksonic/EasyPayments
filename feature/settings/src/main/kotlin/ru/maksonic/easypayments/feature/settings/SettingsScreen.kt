package ru.maksonic.easypayments.feature.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.easypayments.common.ui.BaseScreen
import ru.maksonic.easypayments.feature.settings.core.Eff
import ru.maksonic.easypayments.feature.settings.core.Model
import ru.maksonic.easypayments.feature.settings.core.Msg
import ru.maksonic.easypayments.feature.settings.core.SettingsSandbox
import ru.maksonic.easypayments.feature.settings.databinding.ScreenSettingsBinding
import ru.maksonic.easypayments.navigation.router.Router

/**
 * @Author maksonic on 29.11.2023
 */
class SettingsScreen : BaseScreen<ScreenSettingsBinding, Model, Eff>() {
    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> ScreenSettingsBinding
        get() = ScreenSettingsBinding::inflate

    private val router: Router by inject()
    private val sandbox: SettingsSandbox by viewModel()

    override fun render(savedInstanceState: Bundle?) {
        binding.toolBar.setNavigationOnClickListener { sandbox.send(Msg.Ui.OnTopBarBackClicked) }
        sandbox.model.render()
        sandbox.effects.handle()
    }

    override fun renderModel(model: Model) {
        super.renderModel(model)
    }

    override fun handleEffects(eff: Eff) {
        when (eff) {
            is Eff.NavigateBack -> router.onBack(this)
        }
    }
}