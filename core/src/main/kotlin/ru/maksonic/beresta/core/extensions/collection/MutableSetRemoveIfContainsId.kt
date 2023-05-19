package ru.maksonic.beresta.core.extensions.collection

/**
 * @Author maksonic on 25.04.2023
 */
fun MutableSet<Long>.addOrRemoveIfContains(value: Long): Boolean {
    return if (this.contains(value)) this.remove(value) else this.add(value)
}