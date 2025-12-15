package com.example.calculator

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class CalculatorButtonsExistenceTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun all_expected_buttons_exist() {
        composeRule.setContent { CalculatorScreen(CalculatorViewModel()) }
        val tags = listOf(
            "btn_7","btn_8","btn_9","btn_/",
            "btn_4","btn_5","btn_6","btn_*",
            "btn_1","btn_2","btn_3","btn_-",
            "btn_C","btn_0","btn_=","btn_+"
        )
        tags.forEach { tag ->
            composeRule.onNodeWithTag(tag).assertExists()
        }
    }
}

