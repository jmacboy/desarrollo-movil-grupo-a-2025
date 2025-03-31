package com.example.practicaroom.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.PersonWithPhones
import com.example.practicaroom.repositories.PersonRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _personList: MutableLiveData<List<PersonWithPhones>> =
        MutableLiveData(mutableListOf())
    val personList: LiveData<List<PersonWithPhones>> = _personList

    private val _personDeleted: MutableLiveData<PersonWithPhones> = MutableLiveData(null)
    val personDeleted: LiveData<PersonWithPhones> = _personDeleted

    fun loadData(context: Context) {
        viewModelScope.launch {
            _personList.postValue(PersonRepository.getPersonList(context))
        }
    }

    fun deletePerson(context: Context, person: PersonWithPhones) {
        viewModelScope.launch {
            Log.d("Person", "Deleting person with id ${person.person.id}")
            PersonRepository.deletePerson(context, person.person)
            _personDeleted.postValue(person)
        }
    }
}