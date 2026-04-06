package com.ndejje.momocal

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.darkColorScheme

private val LightColorScheme = lightColorScheme(
    primary = NavyBlue,
    onPrimary = White,
    secondary = BrandedGold,
    onSecondary = NavyBlueDark,
    background = LightGray,
    onBackground = DarkSurface,
    surface = White,
    onSurface = DarkSurface,
    error = ErrorRed,
    onError = OnErrorWhite
)

private val DarkColorScheme =
        darkColorScheme(
            primary = BrandedGold,
            onPrimary = NavyBlueDark,
            secondary = NavyBlue,
            onSecondary = White,
            background = DarkBackground,
            onBackground = OnDarkText,
            surface = DarkSurface,
            onSurface = OnDarkText,
            error = ErrorRed,
            onError = OnErrorWhite
        )

@Composable
fun MoMoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), //auto detect by default
    content: @Composable () -> Unit
){
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = MoMoTypography,
        // from Module 5 Typography.kt
//        shapes = MoMoShapes,
        // from Part D
        content = content
    )
}
