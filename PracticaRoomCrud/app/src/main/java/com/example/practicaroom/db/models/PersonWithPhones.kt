package com.example.practicaroom.db.models

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class PersonWithPhones(
    @Embedded
    val person: Person,
    @Relation(
        parentColumn = "id",
        entityColumn = "personId"
    )
    val phones: List<Phone>
): Serializable