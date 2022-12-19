package ru.maksonic.beresta.elm

/**
 * @Author maksonic on 15.12.2022
 */
data class BaseModel(
    val isIdle: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccessLoading: Boolean = false,
    val isErrorLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isSuccessRefreshing: Boolean = false,
    val isErrorRefreshing: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = "",
)