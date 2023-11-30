package ru.maksonic.easypayments.feature.settings.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.easypayments.feature.settings.core.SettingsProgram
import ru.maksonic.easypayments.feature.settings.core.SettingsSandbox

/**
 * @Author maksonic on 29.11.2023
 */
val settingsUiFeatureModule = module {
    factory { SettingsProgram(logOutUseCase = get(), checkTokenValidityUseCase = get()) }
    viewModel { SettingsSandbox(program = get()) }
}