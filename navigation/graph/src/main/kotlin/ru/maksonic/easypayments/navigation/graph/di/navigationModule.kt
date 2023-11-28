package ru.maksonic.easypayments.navigation.graph.di

import org.koin.dsl.module
import ru.maksonic.easypayments.navigation.graph.AppNavigator
import ru.maksonic.easypayments.navigation.router.Router

/**
 * @Author maksonic on 28.11.2023
 */
val navigationModule = module {
    single<Router> { AppNavigator() }
}