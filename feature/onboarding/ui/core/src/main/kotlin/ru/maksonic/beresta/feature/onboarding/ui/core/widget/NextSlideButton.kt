package ru.maksonic.beresta.feature.onboarding.ui.core.widget

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import ru.maksonic.beresta.common.ui_kit.button.ButtonPrimary
import ru.maksonic.beresta.common.ui_kit.button.ButtonTextPrimary
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.feature.onboarding.ui.core.Msg
import ru.maksonic.beresta.feature.onboarding.ui.core.screen.Send
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 19.02.2023
 */
@Composable
internal fun NextSlideButton(
    send: Send,
    isLastCurrentPage: State<Boolean>,
    isFailFetched: Boolean,
    modifier: Modifier
) {
    val titlePrimaryBtn = if (isLastCurrentPage.value.or(isFailFetched)) text.shared.btnTitleSync
    else text.shared.btnTitleNext

    val alphaDoNotSyncBtn: Float by animateFloatAsState(
        targetValue = if (isLastCurrentPage.value.or(isFailFetched)) 1f else 0f,
        animationSpec = tween(durationMillis = Theme.animVelocity.common, easing = LinearEasing),
        label = ""
    )

    Column(
        modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonPrimary(
            onClick = { send(Msg.Ui.OnNextPageBtnClicked) },
            title = titlePrimaryBtn,
            modifier = modifier.fillMaxWidth()
        )

        Spacer(modifier.height(dp8))

        ButtonTextPrimary(
            onClick = { send(Msg.Ui.OnSkipSyncBtnClicked) },
            title = text.shared.btnTitleSkipSync,
            modifier = modifier.alpha(alphaDoNotSyncBtn)
        )
        Spacer(modifier = modifier.padding(bottom = dp16))
    }
}