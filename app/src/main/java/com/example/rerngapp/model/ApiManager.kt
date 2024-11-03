package com.example.rerngapp.model

import com.example.rerngapp.service.MovieApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    private var dataService: MovieApiService? = null

    fun getDataService():MovieApiService{
        if(dataService == null){

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            dataService = retrofit.create(MovieApiService::class.java)
        }

        return dataService!!
    }

}