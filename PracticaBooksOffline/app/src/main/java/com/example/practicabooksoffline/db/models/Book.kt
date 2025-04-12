package com.example.practicabooksoffline.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    var nombre: String,
    var autor: String,
    var editorial: String,
    var imagen: String,
    var sinopsis: String,
    var isbn: String
) {
    @PrimaryKey
    var id: Long = 0
}