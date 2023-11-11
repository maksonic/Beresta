package ru.maksonic.beresta.feature.tags_list.ui.core

import android.graphics.Color
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import ru.maksonic.beresta.common.ui_kit.bar.top.TopAppBarNormal
import ru.maksonic.beresta.common.ui_kit.button.ButtonPrimary
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_kit.icon.IconBase
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Done
import ru.maksonic.beresta.common.ui_kit.placeholder.PlaceholderLoading
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.colors.surfaceVariant
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.tags_list.ui.core.dialog.AddTagDialog
import ru.maksonic.beresta.platform.core.ui.findActivity

/**
 * @Author maksonic on 05.11.2023
 */
private const val COLOR_TRANSPARENT = Color.TRANSPARENT

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
internal fun Content(model: Model, send: Send, onBack: () -> Unit, modifier: Modifier = Modifier) {
    val scrollBehavior =
        TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val activity = LocalContext.current.findActivity()
    val isDarkTheme = Theme.darkMode.value

    LaunchedEffect(Unit) {
        val navigationBarStyle = with(COLOR_TRANSPARENT) {
            if (isDarkTheme) SystemBarStyle.dark(this)
            else SystemBarStyle.light(this, this)
        }

        activity.enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = COLOR_TRANSPARENT,
                darkScrim = COLOR_TRANSPARENT,
                detectDarkMode = { isDarkTheme }
            ),
            navigationBarStyle = navigationBarStyle
        )
    }

    Box(
        modifier
            .fillMaxSize()
            .background(surface)
            .noRippleClick { },
        contentAlignment = Alignment.BottomCenter
    ) {
        Scaffold(
            topBar = {
                TopAppBarNormal(
                    title = "Список тегов",
                    navIconAction = onBack,
                    scrollBehavior = scrollBehavior
                )
            },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            Box(modifier.padding(paddings), contentAlignment = Alignment.BottomEnd) {
               when {
                   model.base.isLoading -> PlaceholderLoading()
                   model.base.successAfterLoading -> SuccessContent(model, send, modifier)
               }
            }
        }

        ButtonPrimary(
            onClick = { send(Msg.Inner.UpdatedAddTagDialogVisibility(true)) },
            title = "Создать тег",
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(dp16)
        )

        AddTagDialog(model, send)
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SuccessContent(model: Model, send: Send, modifier: Modifier) {
    FlowRow(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(
                start = dp8,
                end = dp8,
                top = dp8,
                bottom = Theme.size.bottomMainBarHeight
            ),
        horizontalArrangement = Arrangement.spacedBy(dp8),
        verticalArrangement = Arrangement.Center
    ) {
        model.tags.data.forEach { tag ->
            FilterChip(
                selected = tag.isSelected,
                enabled = true,
                onClick = { send(Msg.Ui.OnTagClicked(tag.id)) },
                label = { Text(tag.title) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = surface,
                    labelColor = onBackground,
                    selectedContainerColor = surfaceVariant,
                    selectedLabelColor = onBackground
                ),
                leadingIcon = {
                    if (tag.isSelected) {
                        IconBase(
                            icon = AppIcon.Done,
                            tint = onBackground,
                            modifier = Modifier.size(dp16)
                        )
                    }
                }
            )
        }
    }
}