package ru.maksonic.easypayments.feature.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.easypayments.common.ui.BaseScreen
import ru.maksonic.easypayments.common.ui.toastShortTime
import ru.maksonic.easypayments.feature.settings.core.Eff
import ru.maksonic.easypayments.feature.settings.core.Model
import ru.maksonic.easypayments.feature.settings.core.Msg
import ru.maksonic.easypayments.feature.settings.core.SettingsSandbox
import ru.maksonic.easypayments.feature.settings.databinding.ScreenSettingsBinding
import ru.maksonic.easypayments.navigation.router.Router
import ru.maksonic.easypayments.common.ui.R.color.primary
import ru.maksonic.easypayments.common.ui.R.color.error

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
        updatedLogOutBtn(model.isLogOutBtn)
    }

    override fun handleEffects(eff: Eff) {
        when (eff) {
            is Eff.NavigateBack -> router.onBack(this)
            is Eff.NavigateToAuth -> router.navigateFromSettingsToAuth(this)
            is Eff.ShowLogOutErrorToast -> context?.toastShortTime(eff.message)
            is Eff.ShowLogOutSuccessToast -> {
                context?.toastShortTime(getString(R.string.msg_success_log_out))
            }
        }
    }

    private fun updatedLogOutBtn(isLogOut: Boolean) {
        val color = if (isLogOut) error else primary
        val title = if (isLogOut) R.string.btn_title_log_out else R.string.btn_title_log_in
        binding.btnLogOut.apply {
            text = getString(title)
            setBackgroundColor(resources.getColor(color, context.theme))
            setOnClickListener {
                if (isLogOut) {
                    sandbox.send(Msg.Ui.OnLogOutClicked)
                } else {
                    sandbox.send(Msg.Ui.OnAuthClicked)
                }
            }
        }
    }
}