package com.example.practicacalculadora.models

class Calculator {
    private var result: String = ""
    private var prevNumber: Int = 0
    private var currentOperation: OperationType = OperationType.NONE


    fun clearEverything() {
        prevNumber = 0
        currentOperation = OperationType.NONE
        result = ""
    }

    fun clearOne(): String {
        if (result.isEmpty()) {
            return ""
        }
        result = result.dropLast(1);
        return result;
    }

    @Throws(ArithmeticException::class)
    fun solveOperation(): String {
        val currentNumber = result.toInt()
        val operationResult = when (currentOperation) {
            OperationType.ADDITION -> prevNumber + currentNumber
            OperationType.SUBTRACTION -> prevNumber - currentNumber
            OperationType.MULTIPLICATION -> prevNumber * currentNumber
            OperationType.DIVISION -> {
                if (currentNumber != 0) {
                    prevNumber / currentNumber
                } else {
                    throw ArithmeticException("Division by 0")
                }
            }

            OperationType.NONE -> currentNumber
        }
        result = operationResult.toString()
        return result;
    }

    fun startOperation(operationType: OperationType) {
        currentOperation = operationType
        prevNumber = result.toInt()
        result = ""
    }

    fun addNumber(number: Int): String {
        result += number
        result = result.toInt().toString()
        return result
    }


}