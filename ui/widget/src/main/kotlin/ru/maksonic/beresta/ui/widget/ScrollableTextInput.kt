package ru.maksonic.beresta.ui.widget

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.launch

/**
 * @Author maksonic on 27.04.2023
 */
private const val MAX_TEXT_LENGTH = 15000
@Composable
fun ScrollableTextInput(textField: MutableState<TextFieldValue>, scrollState: ScrollState,modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    var height by remember { mutableStateOf(0) }
    var layoutResult: TextLayoutResult? by remember { mutableStateOf(null) }

    BasicTextField(
        value = textField.value,
        onValueChange = { textField.value = it },
        decorationBox = {
            Box(modifier) {
                it()
            }
        },
        onTextLayout = { layoutResult = it },
        textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onBackground),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary),
        maxLines = 1000,
        modifier = modifier
            .fillMaxWidth()
            .onSizeChanged {
                coroutineScope.launch {
                    val cursorInView = textField.value.isCursorInView(
                        layoutResult = layoutResult!!,
                        height = it.height.toFloat(),
                        scrollValue = scrollState.value.toFloat()
                    )
                    if (!cursorInView && height > it.height) {
                        scrollState.scrollBy(
                            textField.value.calculateRequiredSizeScroll(
                                layoutResult = layoutResult!!,
                                oldHeight = height.toFloat(),
                                newHeight = it.height.toFloat(),
                                scrollValue = scrollState.value.toFloat()
                            )
                        )
                    }
                    height = it.height
                }
            }
            .background(Color.Cyan)
    )

    LaunchedEffect(textField.value.selection) {
        val cursorInView = textField.value.isCursorInView(
            layoutResult = layoutResult!!,
            height = height.toFloat(),
            scrollValue = scrollState.value.toFloat()
        )
        if (!cursorInView) {
            scrollState.scrollBy(
                textField.value.calculateRequiredSelectionScroll(
                    layoutResult = layoutResult!!,
                    height = height.toFloat(),
                    scrollValue = scrollState.value.toFloat()
                )
            )
        }
    }
}


fun TextFieldValue.isCursorInView(
    layoutResult: TextLayoutResult,
    height: Float,
    scrollValue: Float
) = with(layoutResult) {
    val currentLine = try {
        getLineForOffset(selection.min)
    } catch (ex: IllegalArgumentException) {
        System.err.println("Corrected Wrong Offset!")
        getLineForOffset(selection.min - 1)
    }
    val lineBottom = getLineBottom(currentLine)
    val lineTop = getLineTop(currentLine)
    lineBottom <= height + scrollValue && lineTop >= scrollValue
}

fun TextFieldValue.calculateRequiredSelectionScroll(
    layoutResult: TextLayoutResult,
    height: Float,
    scrollValue: Float
) = with(layoutResult) {
    val currentLine = try {
        getLineForOffset(selection.min)
    } catch (ex: IllegalArgumentException) {
        System.err.println("Corrected Wrong Offset!")
        getLineForOffset(selection.min - 1)
    }
    val lineTop = getLineTop(currentLine)
    val lineBottom = getLineBottom(currentLine)
    if (lineTop < scrollValue) -(scrollValue - lineTop)
    else if (lineBottom > height + scrollValue) lineBottom - (height + scrollValue)
    else 0f
}

fun TextFieldValue.calculateRequiredSizeScroll(
    layoutResult: TextLayoutResult,
    oldHeight: Float,
    newHeight: Float,
    scrollValue: Float
) = with(layoutResult) {
    val currentLine = try {
        getLineForOffset(selection.min)
    } catch (ex: IllegalArgumentException) {
        System.err.println("Corrected Wrong Offset!")
        getLineForOffset(selection.min - 1)
    }
    val sizeDifference = oldHeight - newHeight
    val lineBottom = getLineBottom(currentLine)
    if (lineBottom in (newHeight + scrollValue)..(oldHeight + scrollValue))
        sizeDifference - (oldHeight - (lineBottom - scrollValue))
    else 0f
}