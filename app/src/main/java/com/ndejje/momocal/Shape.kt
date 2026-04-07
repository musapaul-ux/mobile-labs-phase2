package com.ndejje.momocal

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val MoMoShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp), // chips , tooltips
    small = RoundedCornerShape(8.dp), // TextFiled, buttons
    medium = RoundedCornerShape(16.dp), // cards, dialogs
    large = RoundedCornerShape(24.dp), // bottom sheets
    extraLarge = RoundedCornerShape(28.dp), // FABS, hero containers
)