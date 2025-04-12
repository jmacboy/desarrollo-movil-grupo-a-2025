package com.example.practicabooksoffline.api.dto

import com.example.practicabooksoffline.db.models.Book
import java.io.Serializable

typealias BookDtos = ArrayList<BookDto>

data class BookDto(
    var nombre: String,
    var autor: String,
    var editorial: String,
    var imagen: String,
    var sinopsis: String,
    var isbn: String,
    var calificacion: Long = 0,
    var id: Int = 0,
    var generos: List<GenreDto> = emptyList()
) : Serializable {
    fun toBookEntity(): Book {
        val bookDto = this
        return Book(
            nombre = nombre,
            autor = autor,
            editorial = editorial,
            imagen = imagen,
            sinopsis = sinopsis,
            isbn = isbn,
        ).apply {
            id = bookDto.id.toLong()
        }
    }

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

data class GenreDto(
    val id: Long,
    val nombre: String
)

