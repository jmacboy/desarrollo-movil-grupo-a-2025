package com.example.practicaroom.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.PersonWithPhones

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    suspend fun getAll(): List<PersonWithPhones>

    @Query("SELECT * FROM Person WHERE id = :id")
    suspend fun getById(id: Int): Person

    @Insert
    suspend fun insertPerson(person: Person): Long

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person): Int

    @Query("SELECT * FROM Person WHERE id = :id")
    suspend fun getPersonWithPhones(id: Int): PersonWithPhones
}