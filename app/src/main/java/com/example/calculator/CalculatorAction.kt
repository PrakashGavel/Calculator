package com.example.calculator


sealed class CalculatorAction {
    data class Number(val value: String) : CalculatorAction()
    data class Operator(val value: String) : CalculatorAction()
    object Equals : CalculatorAction()
    object Clear : CalculatorAction()
}