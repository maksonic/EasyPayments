package ru.maksonic.easypayments.feature.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.easypayments.common.ui.BaseScreen
import ru.maksonic.easypayments.common.ui.toastShortTime
import ru.maksonic.easypayments.feature.auth.ui.R
import ru.maksonic.easypayments.feature.auth.ui.databinding.ScreenAuthBinding
import ru.maksonic.easypayments.feature.ui.core.AuthSandbox
import ru.maksonic.easypayments.feature.ui.core.Eff
import ru.maksonic.easypayments.feature.ui.core.Model
import ru.maksonic.easypayments.feature.ui.core.Msg
import ru.maksonic.easypayments.feature.ui.core.VerificationPasswordState
import ru.maksonic.easypayments.feature.ui.core.VerificationUsernameState
import ru.maksonic.easypayments.feature.ui.core.isValid
import ru.maksonic.easypayments.navigation.router.Router

/**
 * @Author maksonic on 28.11.2023
 */
class AuthScreen : BaseScreen<ScreenAuthBinding, Model, Eff>() {
    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> ScreenAuthBinding
        get() = ScreenAuthBinding::inflate

    private val loaderDialog: Dialog by lazy(::initLoaderDialog)
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
        listenUsernameState(model.usernameState)
        listenPasswordState(model.passwordState)
        updatedLoaderDialog(model.usernameState.isValid && model.passwordState.isValid)
    }

    override fun handleEffects(eff: Eff) {
        when (eff) {
            is Eff.NavigateToPayments -> {
                router.navigateFromAuthToPayments(this)
                loaderDialog.dismiss()
            }

            is Eff.ShowTokenFailToast -> context?.toastShortTime(eff.message)
            is Eff.ShowLoaderDialog -> loaderDialog.show()
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

    private fun initLoaderDialog(): Dialog = Dialog(requireActivity()).apply {
        window?.setBackgroundDrawableResource(R.drawable.bg_loader)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(R.layout.dialog_loader)
    }

    private fun listenUsernameState(state: VerificationUsernameState) =
        with(binding.usernameFieldLayout) {
            when (state) {
                is VerificationUsernameState.Empty -> {
                    isErrorEnabled = true
                    error = state.info
                    loaderDialog.dismiss()
                }

                is VerificationUsernameState.Invalid -> {
                    isErrorEnabled = true
                    error = state.info
                    loaderDialog.dismiss()
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
                    loaderDialog.dismiss()
                }

                is VerificationPasswordState.InvalidLength -> {
                    endIconMode = TextInputLayout.END_ICON_NONE
                    isErrorEnabled = true
                    error = state.info
                    loaderDialog.dismiss()
                }

                else -> {
                    endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                    isErrorEnabled = false
                }
            }
        }

    private fun updatedLoaderDialog(isValid: Boolean) {
        if (isValid) {
            val successLoader = loaderDialog.findViewById<ImageView>(R.id.successCheckMark)
            val progress = loaderDialog.findViewById<ProgressBar>(R.id.progress)
            successLoader?.visibility = View.VISIBLE
            progress?.visibility = View.GONE
        }
    }
}