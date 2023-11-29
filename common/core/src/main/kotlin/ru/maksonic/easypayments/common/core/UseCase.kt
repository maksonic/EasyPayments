package ru.maksonic.easypayments.common.core

/**
 * @Author maksonic on 29.11.2023
 */
interface UseCase {
    interface Default<T> {
        operator fun invoke(): T

        interface Async<T> {
            suspend operator fun invoke(): T
        }
    }

    interface WithArgs<out T, in D : Any> {
        operator fun invoke(args: D): T

        interface Async<out T, in D : Any> {
            suspend operator fun invoke(args: D): T
        }
    }
}