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
    val movies = MutableStateFlow<List<ModelMovie>>(listOf())
    val series = MutableStateFlow<List<ModelSerie>>(listOf())
    val actors = MutableStateFlow<List<ModelActor>>(listOf())
    val collections = MutableStateFlow<List<ModelCollection>>(listOf())


    private val _selectedMovie = MutableStateFlow<ModelMovie?>(null)
    val selectedMovie: StateFlow<ModelMovie?> = _selectedMovie

    private val _selectedSerie = MutableStateFlow<ModelSerie?>(null)
    val selectedSerie: StateFlow<ModelSerie?> = _selectedSerie

    private val _selectedActor = MutableStateFlow<ModelActor?>(null)
    val selectedActor: StateFlow<ModelActor?> = _selectedActor


    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(TmdbAPI::class.java)

    val api_key = "571b6cc10de4dc2fdf12b8068b8f3422"


    fun getMovies() {
        viewModelScope.launch {
            try {
                movies.value = api.lastmovies(api_key, "fr").results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                val response = api.searchMovies(api_key, "fr", query)
                if (response.isSuccessful) {
                    response.body()?.let { movieList ->
                        movies.value = movieList.results
                    }
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la recherche de films", e)
            }
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            try {
                val latestSerie = api.lastseries(api_key, "fr")
                series.value = latestSerie.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchSeries(query: String) {
        viewModelScope.launch {
            try {
                val response = api.searchSeries(api_key, "fr", query)
                if (response.isSuccessful) {
                    response.body()?.let { serieList ->
                        series.value = serieList.results
                    }
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la recherche de series", e)
            }
        }
    }

    fun getActors() {
        viewModelScope.launch {
            try {
                val latestActor = api.lastactors(api_key, "fr")
                actors.value = latestActor.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchActors(query: String) {
        viewModelScope.launch {
            try {
                val response = api.searchActors(api_key, "fr", query)
                if (response.isSuccessful) {
                    response.body()?.let { actorList ->
                        actors.value = actorList.results
                    }
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la recherche des acteurs", e)
            }
        }
    }

    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            try {
                val movieDetails = api.getMovieDetails(movieId, api_key, "fr", "credits")
                _selectedMovie.value = movieDetails
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getSerieById(serieId: Int) {
        viewModelScope.launch {
            try {
                val serieDetails = api.getSerieDetails(serieId, api_key, "fr", "credits")
                _selectedSerie.value = serieDetails
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getActorById(actorId: Int) {
        viewModelScope.launch {
            try {
                val actorDetails = api.getActorDetails(actorId, api_key, "fr", "credits")
                _selectedActor.value = actorDetails
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchCollections() {
        viewModelScope.launch {
            try {
                val response = api.searchCollections(api_key, "fr", "horror")
                if (response.isSuccessful) {
                    response.body()?.let { collectionList ->
                        collections.value = collectionList.results
                    }
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erreur lors de la recherche de films", e)
            }
        }
    }
}
