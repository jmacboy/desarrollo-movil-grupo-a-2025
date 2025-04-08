package com.example.practicanavigation.ui.fragments.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondViewModel : ViewModel() {
    private var _userLoggedIn: MutableLiveData<String> = MutableLiveData("")
    val userLoggedIn: LiveData<String> = _userLoggedIn
    private var _loginError: MutableLiveData<Boolean> = MutableLiveData(false)
    val loginError: LiveData<Boolean> = _loginError

    fun login(username: String?, password: String?) {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            _loginError.value = true
            return
        }
        if (username == "admin" && password == "admin") {
            _userLoggedIn.value = username!!
            _loginError.value = false
        } else {
            _loginError.value = true
        }
    }

}