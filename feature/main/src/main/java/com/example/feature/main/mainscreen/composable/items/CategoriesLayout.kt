package com.example.feature.main.mainscreen.composable.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.core.design.theme.Grey60

internal fun LazyListScope.categoriesLayout(
    categories: List<String>,
    onCategorySelect: (String) -> Unit,
) {
    item {
        LazyRow {
            item {
                Spacer(modifier = Modifier.width(16.dp))
            }

            items(items = categories, key = { it }) {
                CategoryItem(
                    name = it,
                    onClick = { onCategorySelect(it) },
                )
            }
        }
    }
    item {
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CategoryItem(
    name: String,
    onClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = Grey60,
                shape = CircleShape,
            )
            .height(32.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = name,
            color = Grey60,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
