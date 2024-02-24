package com.example.feature.start.startscreen.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.design.R
import com.example.core.design.theme.ExampleTheme
import com.example.feature.start.startscreen.StartNavigationEvent
import com.example.feature.start.startscreen.StartNavigator
import com.example.feature.start.startscreen.StartViewModel
import com.ramcosta.composedestinations.annotation.Destination

const val GET_STARTED_BUTTON_TEST_TAG = "GET_STARTED_BUTTON_TEST_TAG"

@Destination
@Composable
fun StartScreen(
    startNavigator: StartNavigator,
    viewModel: StartViewModel = hiltViewModel(),
) {
    val navigationEvent by viewModel.navigationEventFlow.collectAsStateWithLifecycle()

    StartNavigationHandler(
        navigationEvent = navigationEvent,
        navigator = startNavigator,
        onConsumeNavigationEvent = viewModel::onNavigationHandled,
    )

    StartScreenContent(
        onClick = viewModel::onGetStarted,
    )
}

@Composable
internal fun StartScreenContent(
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        ) {
            Image(
                modifier = Modifier.width(150.dp),
                painter = painterResource(id = R.drawable.ecommerce_shop_app),
                contentDescription = null,
            )
            Text(
                text = stringResource(id = R.string.start_screen_welcome),
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = stringResource(id = R.string.start_screen_description),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        BottomButton(onClick)
    }
}

@Preview(showBackground = true)
@Composable
private fun StartScreenContentPreview() {
    ExampleTheme {
        StartScreenContent({})
    }
}

@Composable
private fun BottomButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(GET_STARTED_BUTTON_TEST_TAG),
        onClick = onClick,
    ) {
        Text(text = stringResource(id = R.string.start_screen_get_started))
    }
}

@Composable
internal fun StartNavigationHandler(
    navigationEvent: StartNavigationEvent,
    navigator: StartNavigator,
    onConsumeNavigationEvent: () -> Unit,
) {
    LaunchedEffect(key1 = navigationEvent) {
        if (navigationEvent !is StartNavigationEvent.Idle) {
            onConsumeNavigationEvent()
        }

        when (navigationEvent) {
            StartNavigationEvent.Idle -> Unit
            StartNavigationEvent.ShowMainScreen -> navigator.showMainScreen()
        }
    }
}
