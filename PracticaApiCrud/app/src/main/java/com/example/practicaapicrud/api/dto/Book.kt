package com.example.practicaapicrud.api.dto

import java.io.Serializable

typealias Books = ArrayList<Book>

data class Book(
    var nombre: String,
    var autor: String,
    var editorial: String,
    var imagen: String,
    var sinopsis: String,
    var isbn: String,
    var calificacion: Long = 0,
    var id: Long = 0,
    var generos: List<Genre> = emptyList()
) : Serializable {
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        0,
        0,
        emptyList()
    )
}

data class Genre(
    val id: Long,
    val nombre: String
)

