package com.example.practicarecyclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicarecyclerview.models.Person
import com.example.practicarecyclerview.repositories.PersonRepository

class PersonDetailViewModel : ViewModel() {
    private val _person: MutableLiveData<Person> = MutableLiveData(null)
    val person: LiveData<Person> = _person

    fun loadPerson(id: Int) {
        _person.value = PersonRepository.getPersonById(id)
    }
}