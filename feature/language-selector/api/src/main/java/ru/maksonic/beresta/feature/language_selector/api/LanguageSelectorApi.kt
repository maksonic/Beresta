package ru.maksonic.beresta.feature.language_selector.api

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import androidx.compose.material.ModalBottomSheetState

/**
 * @Author maksonic on 16.02.2023
 */
interface LanguageSelectorApi {

    interface Lang {
        suspend fun setLanguage(lang: AppLanguage)
        val currentLanguage: Flow<AppLanguage>
    }

    interface Ui {
        @OptIn(ExperimentalMaterialApi::class)
        @Composable
        fun BottomSheet(state: () -> ModalBottomSheetState, modifier: Modifier)
    }
}