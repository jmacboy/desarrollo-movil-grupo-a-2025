package com.example.practicarecyclerview.repositories

import com.example.practicarecyclerview.models.Person

object PersonRepository {
    private val personList = mutableListOf(
        Person(1, "John", "Doe", 30, "1234567890", "jhon@test.com"),
        Person(2, "Jane", "Doe", 25, "1234567890", "jane@test.com"),
        Person(3, "Foo", "Bar", 35, "1234567890", "foo@test.com"),
        Person(4, "Juan", "Perez", 40, "1234567890", "juan@test.com"),
        Person(5, "Maria", "Gonzalez", 20, "1234567890", "maria@test.com"),
        Person(6, "Pedro", "Jimenez", 50, "1234567890", "pedro@test.com"),
        Person(7, "Luis", "Gomez", 45, "1234567890", "luis@test.com"),
        Person(8, "John", "Doe", 30, "1234567890", "jhon@test.com"),
        Person(9, "Jane", "Doe", 25, "1234567890", "jane@test.com"),
        Person(10, "Foo", "Bar", 35, "1234567890", "foo@test.com"),
        Person(11, "Juan", "Perez", 40, "1234567890", "juan@test.com"),
        Person(12, "Maria", "Gonzalez", 20, "1234567890", "maria@test.com"),
        Person(13, "Pedro", "Jimenez", 50, "1234567890", "pedro@test.com"),
        Person(14, "Luis", "Gomez", 45, "1234567890", "luis@test.com"),
    )

    fun getPersonList(): MutableList<Person> {
        return personList
    }

    fun getPersonById(id: Int): Person? {
        return personList.find { it.id == id }
    }

    fun savePerson(person: Person): Int {
        if (person.id == 0) {
            return insertPerson(person)
        } else {
            return updatePerson(person)
        }
    }

    private fun updatePerson(person: Person): Int {
        val position = personList.indexOfFirst { it.id == person.id }
        if (position == -1) {
            return 0
        }
        personList[position] = person
        return person.id
    }

    private fun insertPerson(person: Person): Int {
        val lastId = personList.maxOfOrNull { it.id } ?: 0
        person.id = lastId + 1
        personList.add(person)
        return lastId + 1
    }

    fun deletePerson(person: Person): Int {
        val position = personList.indexOfFirst { it.id == person.id }
        if (position == -1) {
            return -1
        }
        personList.removeAt(position)
        return position
    }

}