package com.example.practicarecyclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicarecyclerview.models.Person
import com.example.practicarecyclerview.repositories.PersonRepository

class PersonListViewModel : ViewModel() {
    private val _personList: MutableLiveData<List<Person>> = MutableLiveData(mutableListOf())
    val personList: LiveData<List<Person>> = _personList
    fun loadData() {
        _personList.value = PersonRepository.getPersonList()
    }
}