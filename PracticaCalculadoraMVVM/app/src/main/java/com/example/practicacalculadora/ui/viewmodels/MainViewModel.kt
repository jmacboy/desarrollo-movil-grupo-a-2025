package com.example.practicacalculadora.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicacalculadora.models.Calculator
import com.example.practicacalculadora.models.OperationType

class MainViewModel : ViewModel() {
    private val _result: MutableLiveData<String> = MutableLiveData("")
    val result: LiveData<String> = _result

    private val _showError: MutableLiveData<Boolean> = MutableLiveData(false)
    val showError: LiveData<Boolean> = _showError

    private var calculator = Calculator()


    fun clearEverything() {
        _result.value = ""
        calculator.clearEverything()
    }

    fun clearOne() {
        _result.value = calculator.clearOne()
    }

    fun solveOperation() {
        try {
            _result.value = calculator.solveOperation()
        } catch (e: ArithmeticException) {
            _result.value = ""
            _showError.value = true
        }
    }

    fun startOperation(operationType: OperationType) {
        _result.value = ""
        calculator.startOperation(operationType)
    }

    fun addNumber(number: Int) {
        _result.value = calculator.addNumber(number)

    }
}