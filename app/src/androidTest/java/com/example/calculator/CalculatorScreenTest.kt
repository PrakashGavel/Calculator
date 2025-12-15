package com.example.calculator

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class CalculatorScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun shows_initial_zero() {
        val vm = CalculatorViewModel()
        composeRule.setContent { CalculatorScreen(vm) }
        composeRule.onNodeWithTag("display").assertTextEquals("0")
    }

    @Test
    fun entering_multiple_digits_appends() {
        val vm = CalculatorViewModel()
        composeRule.setContent { CalculatorScreen(vm) }
        composeRule.onNodeWithTag("btn_1").performClick()
        composeRule.onNodeWithTag("btn_2").performClick()
        composeRule.onNodeWithTag("display").assertTextEquals("12")
    }

    @Test
    fun operator_resets_display_and_addition_works() {
        val vm = CalculatorViewModel()
        composeRule.setContent { CalculatorScreen(vm) }
        composeRule.onNodeWithTag("btn_4").performClick()
        composeRule.onNodeWithTag("btn_+").performClick()
        composeRule.onNodeWithTag("display").assertTextEquals("0")
        composeRule.onNodeWithTag("btn_6").performClick()
        composeRule.onNodeWithTag("btn_=").performClick()
        composeRule.onNodeWithTag("display").assertTextEquals("10.0")
    }

    @Test
    fun division_by_zero_results_in_zero() {
        val vm = CalculatorViewModel()
        composeRule.setContent { CalculatorScreen(vm) }
        composeRule.onNodeWithTag("btn_8").performClick()
        composeRule.onNodeWithTag("btn_/").performClick()
        composeRule.onNodeWithTag("btn_0").performClick()
        composeRule.onNodeWithTag("btn_=").performClick()
        composeRule.onNodeWithTag("display").assertTextEquals("0.0")
    }

    @Test
    fun equals_without_operator_keeps_display() {
        val vm = CalculatorViewModel()
        composeRule.setContent { CalculatorScreen(vm) }
        composeRule.onNodeWithTag("btn_9").performClick()
        composeRule.onNodeWithTag("btn_=").performClick()
        composeRule.onNodeWithTag("display").assertTextEquals("9")
    }

    @Test
    fun clear_resets_state() {
        val vm = CalculatorViewModel()
        composeRule.setContent { CalculatorScreen(vm) }
        composeRule.onNodeWithTag("btn_7").performClick()
        composeRule.onNodeWithTag("btn_C").performClick()
        composeRule.onNodeWithTag("display").assertTextEquals("0")
        composeRule.onNodeWithTag("btn_2").performClick()
        composeRule.onNodeWithTag("display").assertTextEquals("2")
    }
}

