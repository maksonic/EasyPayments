package ru.maksonic.easypayments.feature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.easypayments.common.ui.BaseScreen
import ru.maksonic.easypayments.feature.auth.ui.databinding.ScreenAuthBinding
import ru.maksonic.easypayments.feature.ui.core.AuthSandbox
import ru.maksonic.easypayments.feature.ui.core.Eff
import ru.maksonic.easypayments.feature.ui.core.Model
import ru.maksonic.easypayments.feature.ui.core.Msg
import ru.maksonic.easypayments.feature.ui.core.VerificationEmailState
import ru.maksonic.easypayments.feature.ui.core.VerificationPasswordState

/**
 * @Author maksonic on 28.11.2023
 */
class AuthScreen : BaseScreen<ScreenAuthBinding, Model, Eff>() {
    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> ScreenAuthBinding
        get() = ScreenAuthBinding::inflate

    private val sandbox: AuthSandbox by viewModel()

    override fun render(savedInstanceState: Bundle?) {
        initFields()

        binding.btnAuth.setOnClickListener {
            sandbox.send(Msg.Ui.OnAuthBtnClicked)
        }

        sandbox.model.render()
        sandbox.effects.handle()
    }

    override fun renderModel(model: Model) {
        listenEmailState(model.emailState)
        listenPasswordState(model.passwordState)
    }

    override fun handleEffects(eff: Eff) {
        super.handleEffects(eff)
    }

    private fun initFields() {
        binding.emailField.addTextChangedListener {
            sandbox.send(Msg.Inner.UpdatedEmailInput(it.toString()))
        }
        binding.passwordField.addTextChangedListener {
            sandbox.send(Msg.Inner.UpdatedPasswordInput(it.toString()))
        }
    }

    private fun listenEmailState(state: VerificationEmailState) =
        with(binding.emailFieldLayout) {
            when (state) {
                is VerificationEmailState.Empty -> {
                    isErrorEnabled = true
                    error = state.info
                }

                is VerificationEmailState.Invalid -> {
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
}