package com.example.practicaroom.repositories

import android.content.Context
import com.example.practicaroom.db.models.Phone

object PhoneRepository {
    suspend fun insertPhone(context: Context, phone: Phone): Long {
        return RoomRepository
            .getRoomInstance(context)
            .phoneDao()
            .insertPhone(phone)
    }

    suspend fun savePhone(context: Context, phone: Phone): Int {
        if (phone.id == 0) {
            val insertedId = insertPhone(context, phone)
            return insertedId.toInt()
        } else {
            updatePhone(context, phone)
            return phone.id
        }

    }

    suspend fun updatePhone(context: Context, phone: Phone) {
        return RoomRepository
            .getRoomInstance(context)
            .phoneDao()
            .updatePhone(phone)
    }

    suspend fun deletePhone(context: Context, phone: Phone) {
        return RoomRepository
            .getRoomInstance(context)
            .phoneDao()
            .deletePhone(phone)
    }

}