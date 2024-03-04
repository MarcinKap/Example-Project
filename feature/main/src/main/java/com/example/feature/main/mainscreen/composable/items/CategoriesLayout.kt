package com.example.feature.main.mainscreen.composable.items

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.design.theme.Grey50
import com.example.core.design.theme.Grey60

internal fun LazyListScope.categoriesLayout(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelect: (String) -> Unit,
) {
    item {
        Spacer(modifier = Modifier.height(12.dp))
    }
    item {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            item { Spacer(modifier = Modifier.width(12.dp)) }
            items(items = categories, key = { it }) {
                CategoryItem(
                    name = it,
                    isSelected = it == selectedCategory,
                    onClick = { onCategorySelect(it) },
                )
            }
            item { Spacer(modifier = Modifier.width(12.dp)) }
        }
    }
    item {
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun CategoryItem(
    name: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val (backgroundColor, borderColor, textColor) =
        if (isSelected) {
            Triple(Grey50, Grey50, Color.White)
        } else {
            Triple(Color.White, Grey60, Grey60)
        }

    val rippleEffect: Indication = rememberRipple(bounded = true)
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = CircleShape,
            )
            .height(32.dp)
            .clickable(
                enabled = !isSelected,
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rippleEffect,
            )
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = name,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
