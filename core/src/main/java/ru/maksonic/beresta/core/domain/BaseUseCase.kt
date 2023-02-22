package ru.maksonic.beresta.core.domain

/**
 * @Author maksonic on 21.02.2023
 */
interface BaseUseCase {
    interface Default<T> {
        suspend operator fun invoke(): T
    }
    interface WithArgs<out T, in D: Any> {
        suspend operator fun invoke(args: D): T
    }
}