package ru.maksonic.beresta.common.domain

/**
 * @Author maksonic on 21.02.2023
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