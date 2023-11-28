package ru.maksonic.easypayments.feature.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.easypayments.feature.ui.core.AuthProgram
import ru.maksonic.easypayments.feature.ui.core.AuthSandbox

/**
 * @Author maksonic on 28.11.2023
 */
val authUiFeatureModule = module {
    factory { AuthProgram(repository = get(), resourceProvider = get()) }
    viewModel { AuthSandbox(program = get()) }
}