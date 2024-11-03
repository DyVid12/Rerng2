package com.example.rerngapp.service

import com.example.rerngapp.model.ApiResponse
import com.example.rerngapp.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "98c7299151e5aceaa7bdf85714f3676e"
    ): ApiResponse<List<Result>>
}

