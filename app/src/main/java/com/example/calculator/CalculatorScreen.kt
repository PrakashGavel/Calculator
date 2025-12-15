package com.example.calculator


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.testTag


/**
 * Calculator screen showing the display and keypad.
 *
 * @param viewModel The [CalculatorViewModel] providing state and actions.
 */
@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = viewModel.display.value,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("display"),
            textAlign = androidx.compose.ui.text.style.TextAlign.End
        )


        CalculatorButtons(viewModel)
    }
}