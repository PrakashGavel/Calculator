package com.example.calculator


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

/**
 * Main entry Activity that hosts the calculator UI.
 * Instantiates a [CalculatorViewModel] and renders [CalculatorScreen].
 */
class MainActivity : ComponentActivity() {


    private val viewModel: CalculatorViewModel by viewModels()


    /**
     * Android lifecycle callback. Sets the Compose content with the calculator screen.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorScreen(viewModel)
        }
    }
}