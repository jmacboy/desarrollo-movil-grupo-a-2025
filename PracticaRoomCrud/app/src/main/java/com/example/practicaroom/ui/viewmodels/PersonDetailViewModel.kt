package com.example.practicaroom.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.Phone
import com.example.practicaroom.repositories.PersonRepository
import com.example.practicaroom.repositories.PhoneRepository
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

    fun savePhone(context: Context, phoneNumber: String, phoneType: String) {
        Log.d("PersonDetailViewModel", "Saving phone with number $phoneNumber and type $phoneType")
        val phone = Phone(phoneNumber, phoneType, _person.value?.id ?: 0)
        viewModelScope.launch {
            try {
                val id = PhoneRepository.savePhone(context, phone)
                phone.id = id
                _phones.postValue(_phones.value?.plus(phone))
            } catch (e: Exception) {
                e.printStackTrace()
                _hasErrorSaving.postValue(true)
            }
        }
    }

    fun deletePhone(context: Context, phone: Phone) {
        viewModelScope.launch {
            PhoneRepository.deletePhone(context, phone)
            _phones.postValue(
                _phones.value?.filter { it.id != phone.id }
            )
        }
    }
}