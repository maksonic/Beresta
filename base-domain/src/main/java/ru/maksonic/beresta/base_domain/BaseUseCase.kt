package ru.maksonic.beresta.base_domain

/**
 * @Author maksonic on 20.12.2022
 */
interface BaseUseCase {
    interface Default<T> {
        suspend operator fun invoke(): T
    }
    interface WithArgs<out T, in D: Any> {
        suspend operator fun invoke(args: D): T
    }
}