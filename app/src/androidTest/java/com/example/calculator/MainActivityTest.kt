package com.example.calculator

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertTextEquals
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun initialDisplay_isZero() {
        composeRule.onNodeWithTag("display").assertExists()
        composeRule.onNodeWithTag("display").assertTextEquals("0")
    }

    @Test
    fun clickingButtons_performsAddition() {
        composeRule.onNodeWithTag("btn_2").performClick()
        composeRule.onNodeWithTag("btn_+").performClick()
        composeRule.onNodeWithTag("btn_3").performClick()
        composeRule.onNodeWithTag("btn_=").performClick()
        composeRule.onNodeWithTag("display").assertTextEquals("5.0")
    }

    @Test
    fun clear_button_resetsDisplay() {
        composeRule.onNodeWithTag("btn_9").performClick()
        composeRule.onNodeWithTag("btn_C").performClick()
        composeRule.onNodeWithTag("display").assertTextEquals("0")
    }
}
