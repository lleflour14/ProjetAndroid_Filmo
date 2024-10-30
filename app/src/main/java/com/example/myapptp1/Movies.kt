package com.example.myapptp1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass

@Composable
fun MoviesScreen(
    viewModel: MainViewModel,
    navController: NavController,
    windowSizeClass: WindowSizeClass
) {
    val movies by viewModel.movies.collectAsState()

    MediaScreen(
        title = "Les p'tits films",
        items = movies,
        onGetItems = { viewModel.getMovies() },
        onSearchItems = { viewModel.searchMovies(it) },
        navController = navController,
        windowSizeClass = windowSizeClass
    ) { movie -> MovieItem(movie, navController, windowSizeClass) }
}


@Composable
fun MovieItem(movie: ModelMovie, navController: NavController, windowSizeClass: WindowSizeClass) {
    MediaItem(
        item = movie,
        navController = navController,
        windowSizeClass = windowSizeClass,
        imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
        title = movie.title,
        releaseDate = movie.release_date,
        destinationRoute = "movieDetails/${movie.id}"
    )
}


@Composable
fun MovieDetailsScreen(
    viewModel: MainViewModel,
    movieId: Int,
    navController: NavController,
    windowSizeClass: WindowSizeClass
) {
    LaunchedEffect(movieId) {
        viewModel.getMovieById(movieId)
    }

    val movie by viewModel.selectedMovie.collectAsState(initial = null)

    movie?.let { movieDetails ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                MediaDetailsHeader(
                    imageUrlBack = movieDetails.backdrop_path,
                    img = movieDetails.poster_path,
                    title = movieDetails.title,
                    releaseDateOrBirth = movieDetails.release_date,
                    genresOrPlace = movieDetails.genres?.joinToString(", ") { it.name },
                    voteAverage = movieDetails.vote_average,
                    windowSizeClass = windowSizeClass,
                    mediaType = "Movie",
                )
            }
            item {
                Text(
                    text = "Synopsis",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item {
                Text(
                    text = movieDetails.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                Text(
                    text = "Acteurs principaux",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item {
                MediaDetailsCast(
                    items = movieDetails.credits?.cast?.take(9) ?: emptyList(),
                    navController = navController,
                    route = "actorDetails"
                )
            }
        }
    }
}
