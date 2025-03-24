package com.example.practicarecyclerview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicarecyclerview.models.Person
import com.example.practicarecyclerview.repositories.PersonRepository

class PersonDetailViewModel : ViewModel() {
    private val _person: MutableLiveData<Person> = MutableLiveData(null)
    val person: LiveData<Person> = _person
    private val _hasErrorSaving: MutableLiveData<Boolean> = MutableLiveData(false)
    val hasErrorSaving: LiveData<Boolean> = _hasErrorSaving

    fun loadPerson(id: Int) {
        _person.value = PersonRepository.getPersonById(id)
    }

    fun savePerson(person: Person): Boolean {
        try {
            PersonRepository.savePerson(person)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            _hasErrorSaving.value = true
            return false
        }
    }
}