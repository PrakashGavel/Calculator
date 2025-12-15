package com.example.calculator


/**
 * Represents all possible user actions in the calculator UI.
 */
sealed class CalculatorAction {
    /**
     * Numeric input.
     * @param value The digit pressed, e.g., "0".."9".
     */
    data class Number(val value: String) : CalculatorAction()

    /**
     * Operator selection.
     * @param value One of "+", "-", "*", "/".
     */
    data class Operator(val value: String) : CalculatorAction()

    /**
     * Execute the current operation (equals button).
     */
    object Equals : CalculatorAction()

    /**
     * Clear the current state and reset the display to "0".
     */
    object Clear : CalculatorAction()
}