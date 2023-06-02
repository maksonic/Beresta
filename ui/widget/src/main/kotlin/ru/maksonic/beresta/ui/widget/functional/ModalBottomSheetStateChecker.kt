package ru.maksonic.beresta.ui.widget.functional

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue

/**
 * @Author maksonic on 01.06.2023
 */
@OptIn(ExperimentalMaterialApi::class)
val ModalBottomSheetState.isTargetExpandedState: Boolean
    get() = this.targetValue == ModalBottomSheetValue.Expanded