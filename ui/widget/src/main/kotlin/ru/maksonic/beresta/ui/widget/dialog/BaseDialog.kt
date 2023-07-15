package ru.maksonic.beresta.ui.widget.dialog

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.scrim
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.widget.button.DialogButton
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 12.07.2023
 */
@Composable
fun BaseDialog(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    btnTitleCancel: String = text.shared.btnTitleClose,
    btnTitleAccept: String = text.shared.btnTitleAccept,
    onCancelClicked: () -> Unit,
    onAcceptClicked: () -> Unit,
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
                        .systemBarsPadding()
                        .imePadding()
                        .padding(dp32)
                        .clip(Shape.cornerExtra)
                        .background(secondaryContainer)
                        .padding(dp16)
                ) {
                    content()

                    Row(
                        modifier.padding(top = dp16),
                        horizontalArrangement = Arrangement.spacedBy(dp16)
                    ) {

                        DialogButton(
                            action = onCancelClicked,
                            title = btnTitleCancel,
                            isDismiss = true,
                            modifier = Modifier.weight(1f)
                        )
                        DialogButton(
                            action = onAcceptClicked,
                            title = btnTitleAccept,
                            isDismiss = false,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}