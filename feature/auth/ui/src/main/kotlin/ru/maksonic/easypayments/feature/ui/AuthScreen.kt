package ru.maksonic.easypayments.feature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.easypayments.common.ui.BaseScreen
import ru.maksonic.easypayments.common.ui.KeyboardController
import ru.maksonic.easypayments.common.ui.toastShortTime
import ru.maksonic.easypayments.feature.auth.ui.databinding.ScreenAuthBinding
import ru.maksonic.easypayments.feature.ui.core.AuthSandbox
import ru.maksonic.easypayments.feature.ui.core.Eff
import ru.maksonic.easypayments.feature.ui.core.Model
import ru.maksonic.easypayments.feature.ui.core.Msg
import ru.maksonic.easypayments.feature.ui.core.VerificationPasswordState
import ru.maksonic.easypayments.feature.ui.core.VerificationUsernameState
import ru.maksonic.easypayments.navigation.router.Router


/**
 * @Author maksonic on 28.11.2023
 */
class AuthScreen : BaseScreen<ScreenAuthBinding, Model, Eff>() {
    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> ScreenAuthBinding
        get() = ScreenAuthBinding::inflate

    private val sandbox: AuthSandbox by viewModel()
    private val router: Router by inject()

    override fun render(savedInstanceState: Bundle?) {
        initInputFields()

        binding.btnAuth.setOnClickListener {
            sandbox.send(Msg.Ui.OnAuthBtnClicked)
        }

        sandbox.model.render()
        sandbox.effects.handle()
    }

    override fun renderModel(model: Model) {
        listenEmailState(model.usernameState)
        listenPasswordState(model.passwordState)
        listenLoader(model.isVisibleLoader)
    }

    override fun handleEffects(eff: Eff) {
        when (eff) {
            is Eff.HideKeyboard -> {
                with(binding) {
                    usernameFieldLayout.clearFocus()
                    passwordFieldLayout.clearFocus()
                }
                (requireActivity() as? KeyboardController)?.hideIme()
            }

            is Eff.NavigateToPayments -> router.navigateFromAuthToPayments(this)
            is Eff.ShowTokenFailToast -> context?.toastShortTime(eff.message)
        }
    }

    private fun initInputFields() {
        binding.usernameField.addTextChangedListener {
            sandbox.send(Msg.Inner.UpdatedUsernameInput(it.toString()))
        }
        binding.passwordField.addTextChangedListener {
            sandbox.send(Msg.Inner.UpdatedPasswordInput(it.toString()))
        }
    }

    private fun listenEmailState(state: VerificationUsernameState) =
        with(binding.usernameFieldLayout) {
            when (state) {
                is VerificationUsernameState.Empty -> {
                    isErrorEnabled = true
                    error = state.info
                }

                is VerificationUsernameState.Invalid -> {
                    isErrorEnabled = true
                    error = state.info
                }

                else -> isErrorEnabled = false
            }
        }

    private fun listenPasswordState(state: VerificationPasswordState) =
        with(binding.passwordFieldLayout) {
            when (state) {
                is VerificationPasswordState.Empty -> {
                    endIconMode = TextInputLayout.END_ICON_NONE
                    isErrorEnabled = true
                    error = state.info
                }

                is VerificationPasswordState.InvalidLength -> {
                    endIconMode = TextInputLayout.END_ICON_NONE
                    isErrorEnabled = true
                    error = state.info
                }

                else -> {
                    endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                    isErrorEnabled = false
                }
            }
        }

    private fun listenLoader(isVisible: Boolean) {
        val visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.loader.visibility = visibility
    }
}