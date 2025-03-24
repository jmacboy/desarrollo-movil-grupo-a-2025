package com.example.practicarecyclerview.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicarecyclerview.models.Person
import com.example.practicarecyclerview.repositories.PersonRepository

class PersonListViewModel : ViewModel() {
    private val _personList: MutableLiveData<MutableList<Person>> = MutableLiveData(mutableListOf())
    val personList: LiveData<MutableList<Person>> = _personList

    fun loadData() {
        _personList.value = PersonRepository.getPersonList()

    }

    fun deletePerson(person: Person): Int {
        Log.d("Person", "Deleting person with id ${person.id}")
        val index = PersonRepository.deletePerson(person)
        return index
    }
}