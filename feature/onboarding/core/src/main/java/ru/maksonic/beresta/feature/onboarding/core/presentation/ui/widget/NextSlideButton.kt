package ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.onboarding.core.presentation.Msg
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.SendMessage
import ru.maksonic.beresta.ui.theme.component.dimenAnimFast
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.button.TertiaryButton

/**
 * @Author maksonic on 19.02.2023
 */
@Composable
internal fun NextSlideButton(send: SendMessage, isLastCurrentPage: Boolean, modifier: Modifier) {
    val titlePrimaryBtn = if (isLastCurrentPage) text.onboarding.btnTitleSync
    else text.onboarding.btnTitleNext

    val alphaDoNotSyncBtn: Float by animateFloatAsState(
        targetValue = if (isLastCurrentPage) 1f else 0f,
        animationSpec = tween(durationMillis = dimenAnimFast, easing = LinearEasing)
    )

    Column(
        modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryButton(
            action = { send(Msg.Ui.OnPrimaryBtnClicked) },
            title = titlePrimaryBtn,
            modifier = modifier.fillMaxWidth()
        )

        Spacer(modifier.height(dp8))

        TertiaryButton(
            action = { send(Msg.Ui.OnSkipSyncBtnClicked) },
            title = text.onboarding.btnTitleSkipSync,
            modifier = modifier.alpha(alphaDoNotSyncBtn)
        )
        Spacer(modifier = modifier.padding(bottom = dp16))
    }
}