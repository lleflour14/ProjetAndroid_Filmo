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
fun SeriesScreen(
    viewModel: MainViewModel,
    navController: NavController,
    windowSizeClass: WindowSizeClass
) {
    val series by viewModel.series.collectAsState()

    MediaScreen(
        title = "Les p'tites séries",
        items = series,
        onGetItems = { viewModel.getSeries() },
        onSearchItems = { viewModel.searchSeries(it) },
        windowSizeClass = windowSizeClass
    ) { serie -> SerieItem(serie, navController, windowSizeClass) }
}


@Composable
fun SerieItem(serie: ModelSerie, navController: NavController, windowSizeClass: WindowSizeClass) {
    MediaItem(
        item = serie,
        navController = navController,
        windowSizeClass = windowSizeClass,
        pictureUrl = "https://image.tmdb.org/t/p/w500${serie.poster_path}",
        title = serie.name,
        releaseDate = serie.first_air_date,
        destinationRoute = "serieDetails/${serie.id}"
    )
}

@Composable
fun SerieDetailsScreen(
    viewModel: MainViewModel,
    serieId: Int,
    navController: NavController,
    windowSizeClass: WindowSizeClass
) {
    LaunchedEffect(serieId) {
        viewModel.getSerieById(serieId)
    }

    val serie by viewModel.selectedSerie.collectAsState(initial = null)

    serie?.let { serieDetails ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                MediaDetailsHeader(
                    pictureUrlBack = serieDetails.backdrop_path,
                    pic = serieDetails.poster_path,
                    title = serieDetails.name,
                    releaseDateOrBirth = serieDetails.last_air_date,
                    genresOrPlace = serieDetails.genres?.joinToString(", ") { it.name },
                    voteAverage = serieDetails.vote_average,
                    windowSizeClass = windowSizeClass,
                    mediaType = "Serie"
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
                    text = serieDetails.overview,
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
                    items = serieDetails.credits?.cast?.take(9) ?: emptyList(),
                    navController = navController,
                    route = "actorDetails"
                )
            }
        }
    }
}

