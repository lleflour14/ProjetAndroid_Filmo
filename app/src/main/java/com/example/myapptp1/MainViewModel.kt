package com.example.myapptp1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<ResultMovie>>(listOf())
    val series = MutableStateFlow<List<ResultTV>>(listOf())
    val acteurs = MutableStateFlow<List<ResultPerson>>(listOf())

    private val _selectedMovie = MutableStateFlow<ModelMovie?>(null)
    val selectedMovie: StateFlow<ModelMovie?> = _selectedMovie

    private val _selectedSerie = MutableStateFlow<ModelTV?>(null)
    val selectedSerie: StateFlow<ModelTV?> = _selectedSerie

    private val _selectedActor = MutableStateFlow<ModelPerson?>(null)
    val selectedActor: StateFlow<ModelPerson?> = _selectedActor
    


    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(TmdbAPI::class.java)

    val api_key = "571b6cc10de4dc2fdf12b8068b8f3422"



    fun getMovies() {
        viewModelScope.launch {
            try {
                movies.value = api.lastmovies(api_key,"fr").results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                // Appelle l'API Retrofit pour rechercher les films en fonction du mot clé
                val response = api.searchMovies(api_key,"fr",query)
                if (response.isSuccessful) {
                    response.body()?.let { movieList ->
                        // Mets à jour le flux des films avec les résultats de la recherche
                        movies.value=movieList.results
                    }
                }
            } catch (e: Exception) {
                // Gestion des erreurs
                Log.e("MainViewModel", "Erreur lors de la recherche de films", e)
            }
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            try {
                val latestSerie = api.lastseries(api_key,"fr")
                series.value = latestSerie.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchSeries(query: String) {
        viewModelScope.launch {
            try {
                // Appelle l'API Retrofit pour rechercher les films en fonction du mot clé
                val response = api.searchSeries(api_key,"fr",query)
                if (response.isSuccessful) {
                    response.body()?.let { serieList ->
                        // Mets à jour le flux des films avec les résultats de la recherche
                        series.value=serieList.results
                    }
                }
            } catch (e: Exception) {
                // Gestion des erreurs
                Log.e("MainViewModel", "Erreur lors de la recherche de series", e)
            }
        }
    }

    fun getActeurs() {
        viewModelScope.launch {
            try {
                val latestActeur = api.lastacteurs(api_key,"fr")
                acteurs.value = latestActeur.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchActeurs(query: String) {
        viewModelScope.launch {
            try {
                // Appelle l'API Retrofit pour rechercher les films en fonction du mot clé
                val response = api.searchActeurs(api_key,"fr",query)
                if (response.isSuccessful) {
                    response.body()?.let { acteurList ->
                        // Mets à jour le flux des films avec les résultats de la recherche
                        acteurs.value=acteurList.results
                    }
                }
            } catch (e: Exception) {
                // Gestion des erreurs
                Log.e("MainViewModel", "Erreur lors de la recherche des acteurs", e)
            }
        }
    }

    fun getMovieById(movieId:Int) {
        viewModelScope.launch {
            try {
                val movieDetails = api.getMovieDetails(movieId, api_key,"fr","credits")
                _selectedMovie.value = movieDetails
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getSerieById(serieId:Int) {
        viewModelScope.launch {
            try {
                val serieDetails = api.getSerieDetails(serieId, api_key,"fr","credits")
                _selectedSerie.value = serieDetails
                Log.d("MainViewModel", "Série récupérée : $serieDetails")

            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la récupération des détails de la série : ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun getActorById(actorId:Int) {
        viewModelScope.launch {
            try {
                val actorDetails = api.getActorDetails(actorId, api_key,"fr","credits")
                _selectedActor.value = actorDetails
                Log.d("MainViewModel", "Série récupérée : $actorDetails")

            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la récupération des détails de l'acteur : ${e.message}")
                e.printStackTrace()
            }
        }
    }
}
