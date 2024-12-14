package com.example.rerngapp.service

import com.example.rerngapp.model.ApiResponse
import com.example.rerngapp.model.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    // Get popular movies
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "98c7299151e5aceaa7bdf85714f3676e"
    ): ApiResponse<List<Result>>

    // Get top-rated movies
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "98c7299151e5aceaa7bdf85714f3676e"
    ): ApiResponse<List<Result>>

    // Get upcoming movies
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "98c7299151e5aceaa7bdf85714f3676e"
    ): ApiResponse<List<Result>>

    // Get now playing movies
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "98c7299151e5aceaa7bdf85714f3676e"
    ): ApiResponse<List<Result>>

    // Get movie details by ID
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "98c7299151e5aceaa7bdf85714f3676e"
    ): ApiResponse<Result>

    @GET("search/movie")
    suspend fun searchMoviesByTitle(
        @Query("query") title: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "98c7299151e5aceaa7bdf85714f3676e"
    ): ApiResponse<List<Result>>


}

