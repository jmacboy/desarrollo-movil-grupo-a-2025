package com.example.practicaapicrud.repositories

object RetrofitRepository {
    fun getRetrofitInstance(): retrofit2.Retrofit {
        return retrofit2.Retrofit.Builder()
            .baseUrl("https://apilibreria.jmacboy.com/api/")
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
    }
}