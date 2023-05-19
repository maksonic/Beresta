package ru.maksonic.beresta.feature.top_bar_counter.core

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterSharedUiState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.theme.icons.SelectAll
import ru.maksonic.beresta.ui.theme.icons.ShareIos
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 17.05.2023
 */

@Composable
internal fun NormalTopBarCounterContainer(
    topBarCounterSharedUiState: SharedUiState<TopBarCounterSharedUiState>,
    isShowShareIcon: Boolean,
    modifier: Modifier = Modifier
) {
    val topBarCounterSharedUiState = topBarCounterSharedUiState.state.collectAsState()

    Box(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.topBarNormalHeight),
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier
                .padding(start = dp4, end = dp4)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconAction(
                icon = { AppIcon.Close },
                action = topBarCounterSharedUiState.value.onCancelClicked
            )

            Spacer(modifier.weight(1f))

            if (isShowShareIcon) {
                IconAction(
                    icon = { AppIcon.ShareIos },
                    action = topBarCounterSharedUiState.value.onShareClicked
                )
            }

            IconAction(
                icon = { AppIcon.SelectAll },
                action = topBarCounterSharedUiState.value.onSelectAllClicked
            )
        }
        Counter(value = topBarCounterSharedUiState.value.countValue)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Counter(value: Int, modifier: Modifier = Modifier) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text.shared.hintSelectedItemsCount,
            style = TextDesign.bodyPrimary.copy(
                color = onPrimary,
                fontWeight = FontWeight.Bold
            )
        )

        AnimatedContent(
            targetState = value,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    SizeTransform(clip = false)
                )
            }, label = ""
        ) { targetCount ->
            Text(
                text = " $targetCount",
                style = TextDesign.bodyPrimary.copy(
                    color = onPrimary,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}