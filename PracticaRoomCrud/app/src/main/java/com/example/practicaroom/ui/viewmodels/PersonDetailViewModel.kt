package com.example.practicaroom.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.Phone
import com.example.practicaroom.repositories.PersonRepository
import kotlinx.coroutines.launch

class PersonDetailViewModel : ViewModel() {
    private val _person: MutableLiveData<Person> = MutableLiveData(null)
    val person: LiveData<Person> = _person

    private val _phones: MutableLiveData<List<Phone>> = MutableLiveData(mutableListOf())
    val phones: LiveData<List<Phone>> = _phones

    private val _hasErrorSaving: MutableLiveData<Boolean> = MutableLiveData(false)
    val hasErrorSaving: LiveData<Boolean> = _hasErrorSaving

    private val _personSaved: MutableLiveData<Person> = MutableLiveData(null)
    val personSaved: LiveData<Person> = _personSaved

    fun loadPerson(context: Context, id: Int) {
        viewModelScope.launch {
            val personWithPhones = PersonRepository.getPersonWithPhones(context, id)
            _phones.postValue(personWithPhones.phones)
            _person.postValue(personWithPhones.person)
        }
    }

    fun savePerson(context: Context, person: Person) {
        viewModelScope.launch {
            try {
                val id = PersonRepository.savePerson(context, person)
                person.id = id
                _personSaved.postValue(person)
            } catch (e: Exception) {
                e.printStackTrace()
                _hasErrorSaving.postValue(true)
            }
        }
    }

    fun savePhone(phoneNumber: String, phoneType: String) {
        
    }
}