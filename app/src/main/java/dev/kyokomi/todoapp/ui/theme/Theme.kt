package dev.kyokomi.todoapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TodoAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val textColor = if (darkTheme) {
        Color.White
    } else {
        Color.Unspecified
    }

    MaterialTheme(
        colors = colors,
        typography = Typography.copy(
            subtitle1 = Typography.subtitle1.copy(color = textColor),
            subtitle2 = Typography.subtitle2.copy(color = textColor),
            body1 = Typography.body1.copy(color = textColor),
            body2 = Typography.body2.copy(color = textColor.copy(alpha = 0.7f)),
        ),
        shapes = Shapes,
        content = content
    )
}
