package ru.maksonic.beresta.common.ui_kit.helpers.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState

/**
 * @Author maksonic on 24.04.2023
 */
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (this.currentPage - page) + this.currentPageOffsetFraction
}