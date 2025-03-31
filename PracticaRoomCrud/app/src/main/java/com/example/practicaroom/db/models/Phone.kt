package com.example.practicaroom.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Phone(
    var number: String,
    var type: String,
    var personId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}