package ru.maksonic.beresta.feature.notes_list.ui.widget

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.maksonic.beresta.feature.notes_list.ui.R
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.*

/**
 * @Author maksonic on 19.01.2023
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun SelectedNotesCounter(
    countNotes: () -> Int,
    isSelectionState: () -> Boolean,
    modifier: Modifier = Modifier
) {

    AnimatedContent(targetState = isSelectionState()) { isShowCounter ->
        if (isShowCounter) {
            Surface(
                modifier.padding(top = dp6),
                shape = Theme.shape.cornerNormal,
            ) {
                Row(
                    modifier
                        .background(tertiaryContainer)
                        .animateContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.txt_helper_select_notes_count),
                        style = TextDesign.captionNormal,
                        modifier = modifier.padding(start = dp8, top = dp4, bottom = dp4)
                    )

                    AnimatedContent(
                        targetState = countNotes(),
                        transitionSpec = {
                            // Compare the incoming number with the previous number.
                            if (targetState > initialState) {
                                // If the target number is larger, it slides up and fades in
                                // while the initial (smaller) number slides up and fades out.
                                slideInVertically { height -> height } + fadeIn() with
                                        slideOutVertically { height -> -height } + fadeOut()
                            } else {
                                // If the target number is smaller, it slides down and fades in
                                // while the initial number slides down and fades out.
                                slideInVertically { height -> -height } + fadeIn() with
                                        slideOutVertically { height -> height } + fadeOut()
                            }.using(
                                // Disable clipping since the faded slide-in/out should
                                // be displayed out of bounds.
                                SizeTransform(clip = false)
                            )
                        }
                    ) { targetCount ->
                        Text(
                            text = "$targetCount",
                            style = TextDesign.captionNormal,
                            modifier = modifier.padding(end = dp8)
                        )
                    }
                }
            }
        }
    }
}