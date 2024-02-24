package com.example.core.testing.compose.extension

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.core.app.ApplicationProvider

val context: Context
    get() = ApplicationProvider.getApplicationContext()

val ComposeTestRule.resources: Resources
    get() =
        checkNotNull(this as? AndroidComposeTestRule<*, *>) {
            "Resources are only available using AndroidComposeTestRule"
        }.activity.resources

fun getString(@StringRes stringId: Int): String =
    context.getString(stringId)

fun getString(@StringRes stringId: Int, vararg formatArgs: String): String =
    context.getString(stringId, *formatArgs)
