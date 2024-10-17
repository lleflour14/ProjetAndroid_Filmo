package com.example.myapptp1

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {

    @GET("trending/movie/week")
    suspend fun lastmovies(
        @Query("api_key") api_key: String, @Query("language") language: String
    ): ModelListMovie

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Response<ModelListMovie>

    @GET("trending/tv/week")
    suspend fun lastseries(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): ModelListTV

    @GET("search/tv")
    suspend fun searchSeries(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Response<ModelListTV>

    @GET("trending/person/week")
    suspend fun lastacteurs(
        @Query("api_key") api_key: String, @Query("language") language: String
    ): ModelListPerson

    @GET("search/person")
    suspend fun searchActeurs(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Response<ModelListPerson>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: String = "credits" // Ajout de credits
    ): ModelMovie

    @GET("tv/{id}")
    suspend fun getSerieDetails(
        @Path("id") serieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: String = "credits" // Ajout de credits
    ): ModelTV

    @GET("person/{id}")
    suspend fun getActorDetails(
        @Path("id") serieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: String = "credits" // Ajout de credits
    ): ModelPerson
}

