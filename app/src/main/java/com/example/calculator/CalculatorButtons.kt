package com.example.calculator


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CalculatorButtons(viewModel: CalculatorViewModel) {
    val buttons = listOf(
        listOf("7", "8", "9", "/"),
        listOf("4", "5", "6", "*"),
        listOf("1", "2", "3", "-"),
        listOf("C", "0", "=", "+")
    )


    Column {
        buttons.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { label ->
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(64.dp),
                        onClick = {
                            when (label) {
                                "C" -> viewModel.onAction(CalculatorAction.Clear)
                                "=" -> viewModel.onAction(CalculatorAction.Equals)
                                "+", "-", "*", "/" -> viewModel.onAction(
                                    CalculatorAction.Operator(label)
                                )
                                else -> viewModel.onAction(CalculatorAction.Number(label))
                            }
                        }
                    ) {
                        Text(text = label)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}