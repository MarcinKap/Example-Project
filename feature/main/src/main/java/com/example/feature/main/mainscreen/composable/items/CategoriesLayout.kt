package com.example.feature.main.mainscreen.composable.items

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.core.design.R

internal fun LazyListScope.categoriesLayout() {
    item {
        CategoryTitle()
    }
    item {
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CategoryTitle() {
    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = stringResource(id = R.string.main_screen_categories_title),
    )
}
