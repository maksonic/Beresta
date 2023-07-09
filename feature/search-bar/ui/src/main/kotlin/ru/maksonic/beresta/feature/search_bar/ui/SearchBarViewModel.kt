package ru.maksonic.beresta.feature.search_bar.ui

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @Author maksonic on 03.07.2023
 */
class SearchBarViewModel : ViewModel() {
    companion object {
        private const val QUERY_MAX_LENGTH = 500
    }

    private val _textFieldValue = MutableStateFlow(TextFieldValue())
    val textFiledValue = _textFieldValue.asStateFlow()

    fun updateQuery(value: TextFieldValue) {
        _textFieldValue.value = value.copy(text = value.text.take(QUERY_MAX_LENGTH))
    }
}