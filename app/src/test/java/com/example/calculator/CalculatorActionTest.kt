package com.example.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorActionTest {

    @Test
    fun numberAction_holdsValue() {
        val action = CalculatorAction.Number("42")
        assertEquals("42", action.value)
    }

    @Test
    fun operatorAction_holdsValue() {
        val action = CalculatorAction.Operator("+")
        assertEquals("+", action.value)
    }
}

