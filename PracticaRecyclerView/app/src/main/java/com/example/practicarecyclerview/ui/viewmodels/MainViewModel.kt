package com.example.practicarecyclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicarecyclerview.repositories.NameListRepository

class MainViewModel : ViewModel() {
    private val _nameList: MutableLiveData<Array<String>> = MutableLiveData(arrayOf())
    val nameList: LiveData<Array<String>> = _nameList
    fun loadData() {
        _nameList.value = NameListRepository.getNames()
    }
}