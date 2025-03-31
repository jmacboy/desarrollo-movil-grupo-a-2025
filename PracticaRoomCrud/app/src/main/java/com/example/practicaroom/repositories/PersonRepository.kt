package com.example.practicaroom.repositories

import android.content.Context
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.PersonWithPhones

object PersonRepository {
    suspend fun getPersonList(context: Context): List<Person> {
        return RoomRepository
            .getRoomInstance(context)
            .personDao()
            .getAll()
    }

    suspend fun getPersonById(context: Context, id: Int): Person {
        if (id <= 0) {
            throw IllegalArgumentException("Id must be greater than 0")
        }
        return RoomRepository
            .getRoomInstance(context)
            .personDao()
            .getById(id)
    }

    suspend fun getPersonWithPhones(context: Context, id: Int): PersonWithPhones {
        if (id <= 0) {
            throw IllegalArgumentException("Id must be greater than 0")
        }
        return RoomRepository
            .getRoomInstance(context)
            .personDao()
            .getPersonWithPhones(id)
    }

    suspend fun insertPerson(context: Context, person: Person): Long {
        return RoomRepository
            .getRoomInstance(context)
            .personDao()
            .insertPerson(person)
    }

    suspend fun savePerson(context: Context, person: Person): Int {
        if (person.id == 0) {
            val insertedId = insertPerson(context, person)
            return insertedId.toInt()
        } else {
            updatePerson(context, person)
            return person.id
        }

    }

    suspend fun updatePerson(context: Context, person: Person) {
        return RoomRepository
            .getRoomInstance(context)
            .personDao()
            .updatePerson(person)
    }

    suspend fun deletePerson(context: Context, person: Person): Int {
        return RoomRepository
            .getRoomInstance(context)
            .personDao()
            .deletePerson(person)
    }
}