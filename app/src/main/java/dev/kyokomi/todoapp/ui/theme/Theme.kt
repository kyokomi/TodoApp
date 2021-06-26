package dev.kyokomi.todoapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.kyokomi.todoapp.model.AccountSettingEntity
import dev.kyokomi.todoapp.ui.main.MainViewModel

private val DarkColorPalette = darkColors(
    primary = Red300,
    primaryVariant = Red700,
    onPrimary = Color.Black,
    secondary = Red300,
    onSecondary = Color.Black,
    error = Red200,
)

private val LightColorPalette = lightColors(
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = Color.White,
    error = Red800,
)

@Composable
fun TodoAppTheme(
    mainViewModel: MainViewModel = viewModel(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val accountSetting by mainViewModel.accountSetting.collectAsState(initial = AccountSettingEntity())

    MaterialTheme(
        colors = if (darkTheme && accountSetting.darkMode) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
