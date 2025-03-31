package com.example.practicaroom.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicaroom.db.dao.PersonDao
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.Phone

@Database(
    entities = [Person::class, Phone::class],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}