package ru.maksonic.beresta.common.ui_kit.dialog

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.button.dialog.ButtonDialogPrimary
import ru.maksonic.beresta.common.ui_kit.button.dialog.ButtonDialogSecondary
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.scrim
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.common.ui_theme.provide.dp32
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 12.07.2023
 */
@Composable
fun DialogBase(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    actionsModifier: Modifier = Modifier,
    isVisibleActions: Boolean = true,
    alignment: Alignment = Alignment.Center,
    titleBtnCancel: String = text.shared.btnTitleClose,
    titleBtnAccept: String = text.shared.btnTitleAccept,
    contentPadding: Dp = dp24,
    onCancelClicked: () -> Unit = {},
    onAcceptClicked: () -> Unit = {},
    content: @Composable () -> Unit
) {

    if (isVisible) {
        BackHandler {
            onCancelClicked()
        }
    }

    AnimateFadeInOut(
        visible = isVisible,
        fadeInDuration = Theme.animVelocity.dialogVisibility,
        fadeOutDuration = Theme.animVelocity.dialogVisibility
    ) {
        SurfacePro(color = scrim) {
            Box(modifier.fillMaxSize(), contentAlignment = alignment) {
                Column(
                    modifier
                        .verticalScroll(rememberScrollState())
                        .systemBarsPadding()
                        .imePadding()
                        .padding(dp32)
                        .clip(Theme.shape.cornerExtra)
                        .background(secondaryContainer)
                        .padding(contentPadding)
                ) {
                    content()

                    if (isVisibleActions) {
                        Row(
                            actionsModifier.padding(top = dp24),
                            horizontalArrangement = Arrangement.spacedBy(dp16)
                        ) {
                            ButtonDialogSecondary(
                                title = titleBtnCancel,
                                onClick = onCancelClicked,
                                modifier = Modifier.weight(1f)
                            )

                            ButtonDialogPrimary(
                                title = titleBtnAccept,
                                onClick = onAcceptClicked,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}