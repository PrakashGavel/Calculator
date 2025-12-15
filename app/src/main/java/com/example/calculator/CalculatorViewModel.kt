package com.example.calculator
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class CalculatorViewModel : ViewModel() {


    var display = mutableStateOf("0")
        private set


    private var operand1: Double? = null
    private var operator: String? = null


    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> appendNumber(action.value)
            is CalculatorAction.Operator -> setOperator(action.value)
            CalculatorAction.Clear -> clear()
            CalculatorAction.Equals -> calculate()
        }
    }


    private fun appendNumber(number: String) {
        display.value = if (display.value == "0") number else display.value + number
    }


    private fun setOperator(op: String) {
        operand1 = display.value.toDoubleOrNull()
        operator = op
        display.value = "0"
    }


    private fun calculate() {
        val operand2 = display.value.toDoubleOrNull()
        if (operand1 != null && operand2 != null && operator != null) {
            val result = when (operator) {
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "*" -> operand1!! * operand2
                "/" -> if (operand2 != 0.0) operand1!! / operand2 else 0.0
                else -> 0.0
            }
            display.value = result.toString()
            operand1 = null
            operator = null
        }
    }


    private fun clear() {
        display.value = "0"
        operand1 = null
        operator = null
    }
}