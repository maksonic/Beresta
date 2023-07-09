package ru.maksonic.beresta.feature.top_bar_counter.core

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.theme.icons.SelectAll
import ru.maksonic.beresta.ui.theme.icons.ShareIos
import ru.maksonic.beresta.ui.widget.button.ClickableIcon

/**
 * @Author maksonic on 30.04.2023
 */
class TopBarCounterWidget : TopBarCounterApi.Ui {
    override val counterState: MutableState<Int> = mutableIntStateOf(1)

    @Composable
    override fun Widget(
        onCancelClicked: () -> Unit,
        onSelectAllClicked: () -> Unit,
        onShareSelectedClicked: () -> Unit
    ) {
        Content(counterState, onCancelClicked, onSelectAllClicked, onShareSelectedClicked)
    }
}

@Composable
private fun Content(
    state: MutableState<Int>,
    onCancelClicked: () -> Unit,
    onSelectAllClicked: () -> Unit,
    onShareSelectedClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            ClickableIcon(
                icon = AppIcon.Close,
                action = onCancelClicked
            )

            Spacer(modifier.weight(1f))

            ClickableIcon(
                icon = AppIcon.ShareIos,
                action = onShareSelectedClicked
            )

            ClickableIcon(
                icon = AppIcon.SelectAll,
                action = onSelectAllClicked
            )
        }
        Counter(state)
    }
}

@Composable
private fun Counter(state: State<Int>, modifier: Modifier = Modifier) {

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
            targetState = state.value,
            transitionSpec = {
                if (targetState > initialState) {
                    (slideInVertically { height -> height } + fadeIn()).togetherWith(
                        slideOutVertically { height -> -height } + fadeOut())
                } else {
                    (slideInVertically { height -> -height } + fadeIn()).togetherWith(
                        slideOutVertically { height -> height } + fadeOut())
                }.using(
                    SizeTransform(clip = false)
                )
            }, label = ""
        ) { targetCount ->
            Text(
                text = " $targetCount",
                style = TextDesign.bodyPrimary.copy(
                    color = onPrimary, fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
