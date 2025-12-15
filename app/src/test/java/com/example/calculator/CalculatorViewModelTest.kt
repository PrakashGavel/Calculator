package com.example.calculator

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun initialDisplay_isZero() {
        assertEquals("0", viewModel.display.value)
    }

    @Test
    fun appendNumber_buildsDigits() {
        viewModel.onAction(CalculatorAction.Number("5"))
        assertEquals("5", viewModel.display.value)
        viewModel.onAction(CalculatorAction.Number("3"))
        assertEquals("53", viewModel.display.value)
    }

    @Test
    fun setOperator_resetsDisplay_andStoresOperand1() {
        viewModel.onAction(CalculatorAction.Number("12"))
        viewModel.onAction(CalculatorAction.Operator("+"))
        assertEquals("0", viewModel.display.value)
        viewModel.onAction(CalculatorAction.Number("7"))
        assertEquals("7", viewModel.display.value)
    }

    @Test
    fun addition_works() {
        viewModel.onAction(CalculatorAction.Number("2"))
        viewModel.onAction(CalculatorAction.Operator("+"))
        viewModel.onAction(CalculatorAction.Number("3"))
        viewModel.onAction(CalculatorAction.Equals)
        assertEquals("5.0", viewModel.display.value)
    }

    @Test
    fun subtraction_works() {
        viewModel.onAction(CalculatorAction.Number("7"))
        viewModel.onAction(CalculatorAction.Operator("-"))
        viewModel.onAction(CalculatorAction.Number("4"))
        viewModel.onAction(CalculatorAction.Equals)
        assertEquals("3.0", viewModel.display.value)
    }

    @Test
    fun multiplication_works() {
        viewModel.onAction(CalculatorAction.Number("6"))
        viewModel.onAction(CalculatorAction.Operator("*"))
        viewModel.onAction(CalculatorAction.Number("3"))
        viewModel.onAction(CalculatorAction.Equals)
        assertEquals("18.0", viewModel.display.value)
    }

    @Test
    fun division_works() {
        viewModel.onAction(CalculatorAction.Number("8"))
        viewModel.onAction(CalculatorAction.Operator("/"))
        viewModel.onAction(CalculatorAction.Number("2"))
        viewModel.onAction(CalculatorAction.Equals)
        assertEquals("4.0", viewModel.display.value)
    }

    @Test
    fun divisionByZero_returnsZero() {
        viewModel.onAction(CalculatorAction.Number("8"))
        viewModel.onAction(CalculatorAction.Operator("/"))
        viewModel.onAction(CalculatorAction.Number("0"))
        viewModel.onAction(CalculatorAction.Equals)
        assertEquals("0.0", viewModel.display.value)
    }

    @Test
    fun equals_withoutCompleteState_keepsDisplay() {
        viewModel.onAction(CalculatorAction.Number("9"))
        viewModel.onAction(CalculatorAction.Equals)
        assertEquals("9", viewModel.display.value)
    }

    @Test
    fun clear_resetsState() {
        viewModel.onAction(CalculatorAction.Number("9"))
        viewModel.onAction(CalculatorAction.Operator("*"))
        viewModel.onAction(CalculatorAction.Clear)
        assertEquals("0", viewModel.display.value)
        viewModel.onAction(CalculatorAction.Number("2"))
        assertEquals("2", viewModel.display.value)
    }
}

