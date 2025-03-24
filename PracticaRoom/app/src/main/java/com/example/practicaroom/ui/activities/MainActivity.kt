package com.example.practicaroom.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.practicaroom.R
import com.example.practicaroom.db.AppDatabase
import com.example.practicaroom.db.models.Person

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "prueba-db"
        ).allowMainThreadQueries()
            .build()
        val personDao = db.personDao()
        personDao.insertPerson(
            Person(
                "Juan",
                "Perez",
                25,
                "juan@test.com",
                "1234567890",
                "1996-01-01"
            )
        )
        val person = personDao.getById(1)
        Log.d("Inserted Person", person.toString())
    }
}