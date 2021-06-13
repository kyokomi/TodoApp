package dev.kyokomi.todoapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.kyokomi.todoapp.R

private val Sora = FontFamily(
    Font(R.font.sora_regular),
    Font(R.font.sora_medium, FontWeight.W500),
    Font(R.font.sora_semibold, FontWeight.W600),
)

private val Domine = FontFamily(
    Font(R.font.domine_regular),
    Font(R.font.domine_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    h4 = TextStyle(
        fontFamily = Sora,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp,
    ),
    h5 = TextStyle(
        fontFamily = Sora,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
    ),
    h6 = TextStyle(
        fontFamily = Sora,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = Sora,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = Sora,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
    ),
    body1 = TextStyle(
        fontFamily = Domine,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Domine,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = Sora,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = Sora,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = Sora,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
)
