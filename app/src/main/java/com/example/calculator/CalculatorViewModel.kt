package com.example.calculator
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * ViewModel that holds calculator UI state and business logic.
 *
 * Responsibilities:
 * - Expose the current display as state.
 * - Accept UI actions and mutate state accordingly.
 * - Perform basic binary operations (+, -, *, /) on demand.
 */
class CalculatorViewModel : ViewModel() {


    /**
     * Current calculator display text.
     * Starts at "0" and updates with user input and results.
     */
    var display = mutableStateOf("0")
        private set


    private var operand1: Double? = null
    private var operator: String? = null


    /**
     * Entry point for all UI actions.
     * Dispatches to a specific handler based on the action type.
     *
     * @param action The [CalculatorAction] triggered by UI.
     */
    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> appendNumber(action.value)
            is CalculatorAction.Operator -> setOperator(action.value)
            CalculatorAction.Clear -> clear()
            CalculatorAction.Equals -> calculate()
        }
    }


    /**
     * Append a digit to the display. If the display is currently "0",
     * it will be replaced by the provided digit.
     *
     * @param number String representing a single digit (e.g., "0".."9").
     */
    private fun appendNumber(number: String) {
        display.value = if (display.value == "0") number else display.value + number
    }


    /**
     * Store the current display value as the first operand and the operator,
     * then reset the display to "0" to accept the second operand.
     *
     * @param op One of "+", "-", "*", "/".
     */
    private fun setOperator(op: String) {
        operand1 = display.value.toDoubleOrNull()
        operator = op
        display.value = "0"
    }


    /**
     * Execute the pending operation using the stored first operand, the current display as the
     * second operand, and the selected operator. Division by zero returns 0.0.
     * After calculation, the operator and first operand are cleared.
     */
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


    /**
     * Reset the calculator to its initial state: display to "0" and
     * clear any stored operand/operator.
     */
    private fun clear() {
        display.value = "0"
        operand1 = null
        operator = null
    }
}