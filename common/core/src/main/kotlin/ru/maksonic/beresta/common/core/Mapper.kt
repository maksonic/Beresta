package ru.maksonic.beresta.common.core

/**
 * @Author maksonic on 13.10.2023
 */
interface Mapper<I, O> {
    fun mapTo(i: I): O
    fun mapFrom(o: O): I
    fun mapListTo(list: List<I>?): List<O> = list?.mapNotNull { mapTo(it) } ?: emptyList()
    fun mapListFrom(list: List<O>?): List<I> = list?.mapNotNull { mapFrom(it) } ?: emptyList()
}