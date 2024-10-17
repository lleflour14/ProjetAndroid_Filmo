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

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: String = "credits"
    ): ModelMovie


    @GET("trending/tv/week")
    suspend fun lastseries(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): ModelListSerie

    @GET("search/tv")
    suspend fun searchSeries(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Response<ModelListSerie>

    @GET("tv/{id}")
    suspend fun getSerieDetails(
        @Path("id") serieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: String = "credits"
    ): ModelSerie


    @GET("trending/person/week")
    suspend fun lastactors(
        @Query("api_key") api_key: String, @Query("language") language: String
    ): ModelListActor

    @GET("search/person")
    suspend fun searchActors(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Response<ModelListActor>

    @GET("person/{id}")
    suspend fun getActorDetails(
        @Path("id") serieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: String = "credits"
    ): ModelActor
}

