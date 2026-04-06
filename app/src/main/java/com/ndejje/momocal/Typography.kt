package com.ndejje.momocal

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.ndejje.momocal.R

val PlayfairDisplay = FontFamily(
    Font(R.font.playfair_display_regular, FontWeight.Normal),
    Font(R.font.playfair_display_bold, FontWeight.Bold)
)

val SourceSans3 = FontFamily(
    Font(R.font.source_sans3_regular, FontWeight.Normal),
    Font(R.font.source_sans3_semi_bold, FontWeight.SemiBold)
)

val MoMoTypography = Typography(
    // App title-large, bold serif
    headlineMedium = TextStyle(
        fontFamily = PlayfairDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),

    // fee result label
    bodyLarge = TextStyle(
        fontFamily = SourceSans3,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),

    // Input field label and all general text
    bodyMedium = TextStyle(
        fontFamily = SourceSans3,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    // Error message text
    bodySmall = TextStyle(
        fontFamily = SourceSans3,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)