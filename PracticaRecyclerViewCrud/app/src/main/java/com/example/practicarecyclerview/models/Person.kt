package com.example.practicarecyclerview.models

import java.io.Serializable

class Person() : Serializable {
    var id: Int = 0
    var name: String = ""
    var lastName: String = ""
    var age: Int = 0
    var phone: String = ""
    var email: String = ""

    constructor(
        id: Int,
        name: String,
        lastName: String,
        age: Int,
        phone: String,
        email: String
    ) : this() {
        this.id = id
        this.name = name
        this.lastName = lastName
        this.age = age
        this.phone = phone
        this.email = email
    }

    override fun toString(): String {
        return "Person(id=$id, name='$name', lastName='$lastName', age=$age, phone='$phone', email='$email')"
    }
}