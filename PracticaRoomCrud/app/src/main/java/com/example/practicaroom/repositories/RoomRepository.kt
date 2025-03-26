package com.example.practicaroom.repositories

import android.content.Context
import androidx.room.Room
import com.example.practicaroom.db.AppDatabase

object RoomRepository {
    fun getRoomInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "prueba-db"
        ).build()
    }
}