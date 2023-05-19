package ru.maksonic.beresta.core

/**
 * @Author maksonic on 15.12.2022
 */
interface Mapper<I, O> {
    fun mapTo(i: I): O
    fun mapFrom(o: O): I
    fun mapListTo(list: List<I>?): List<O> = list?.mapNotNull { mapTo(it) } ?: emptyList()
    fun mapListFrom(list: List<O>?): List<I> = list?.mapNotNull { mapFrom(it) } ?: emptyList()
}