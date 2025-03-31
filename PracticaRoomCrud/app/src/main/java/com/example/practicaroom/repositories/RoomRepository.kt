package com.example.practicaroom.repositories

import android.content.Context
import androidx.room.Room
import com.example.practicaroom.db.AppDatabase
import com.example.practicaroom.db.migrations.MigrationFrom1To2

object RoomRepository {
    private val MIGRATION_1_2 = MigrationFrom1To2()
    fun getRoomInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "prueba-db"
        ).addMigrations(MIGRATION_1_2)
            .build()
    }
}