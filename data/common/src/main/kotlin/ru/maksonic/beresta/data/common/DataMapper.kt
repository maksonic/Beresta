package ru.maksonic.beresta.data.common

/**
 * @Author maksonic on 19.12.2022
 */
interface DataMapper<I, O> {
    fun dataToDomain(i: I): O
    fun domainToData(o: O): I
    fun listDataToDomain(list: List<I>?): List<O> =
        list?.mapNotNull { dataToDomain(it) } ?: emptyList()

    fun listDomainToData(list: List<O>?): List<I> =
        list?.mapNotNull { domainToData(it) } ?: emptyList()
}