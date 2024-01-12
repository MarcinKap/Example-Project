package com.example.core.design.iconbutton

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun IconButton(
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    androidx.compose.material3.IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
        )
    }
}
