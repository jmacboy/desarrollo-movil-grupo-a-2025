package com.example.practicaroom.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class MigrationFrom1To2 : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS `Phone` (`number` TEXT NOT NULL, `personId` INTEGER NOT NULL,`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type` TEXT NOT NULL);"
        )
        db.execSQL("ALTER TABLE Person DROP COLUMN phone;")
    }
}