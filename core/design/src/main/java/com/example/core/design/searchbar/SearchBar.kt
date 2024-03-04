package com.example.core.design.searchbar

import CustomTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.imeAction
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.design.theme.ExampleTheme
import com.example.core.design.theme.Grey10
import com.example.core.design.theme.Grey60
import com.example.core.design.theme.Grey80

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }
    val textFieldValue = textFieldValueState.copy(text = value)

    SideEffect {
        if (textFieldValue.selection != textFieldValueState.selection ||
            textFieldValue.composition != textFieldValueState.composition
        ) {
            textFieldValueState = textFieldValue
        }
    }
    var lastTextValue by remember(value) { mutableStateOf(value) }

    Box(modifier = modifier.padding(vertical = 6.dp)) {
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    this.contentDescription = contentDescription
                    this.imeAction = ImeAction.Done
                },
            value = textFieldValue,
            onValueChange = { newTextFieldValueState ->
                textFieldValueState = newTextFieldValueState

                val stringChangedSinceLastInvocation = lastTextValue != newTextFieldValueState.text
                lastTextValue = newTextFieldValueState.text

                if (stringChangedSinceLastInvocation) {
                    onValueChange(newTextFieldValueState.text)
                }
            },
            keyboardActions = KeyboardActions(
                onDone = { onSearch(value) },
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = if (placeholder != null) {
                @Composable {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            } else {
                null
            },
            trailingIcon = {
                if (value.isNotBlank()) {
                    IconButton(
                        onClick = { onValueChange("") },
                        content = {
                            Icon(
                                imageVector = Icons.Default.Close,
                                tint = MaterialTheme.colorScheme.onSurface,
                                contentDescription = null,
                            )
                        },
                    )
                }
            },
            container = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Grey80,
                            shape = CircleShape,
                        ),
                )
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    ExampleTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SearchBar(
                value = "",
                placeholder = "Placeholder",
                onValueChange = {},
                onSearch = {},
            )
            SearchBar(
                value = "Example",
                placeholder = "Placeholder",
                onValueChange = {},
                onSearch = {},
            )
        }
    }
}

private object SearchBarTextStyle {
    val TextFieldColors
        @Composable
        get() = MaterialTheme.typography.bodyLarge.copy(color = Grey10)

    val LabelColors
        @Composable
        get() = MaterialTheme.typography.bodyLarge.copy(color = Grey60)
}
