package com.example.myapptp1


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun FilmDetailsScreen(
    viewModel: MainViewModel,
    movieId: Int,
    navController: NavController,
    windowClass: WindowSizeClass
) {
    // Appeler la fonction pour récupérer les détails du film
    LaunchedEffect(movieId) {
        viewModel.getMovieById(movieId)
    }

    val movie by viewModel.selectedMovie.collectAsState()

    when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            movie?.let { movieDetails ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${movieDetails.backdrop_path}",
                            contentDescription = "Image du film",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )
                    }
                    item {
                        Text(
                            text = movieDetails.title,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    item {
                        Row(modifier = Modifier.padding(16.dp)) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500${movieDetails.poster_path}",
                                contentDescription = null,
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(225.dp)
                            )
                            Column(modifier = Modifier.padding(start = 16.dp)) {
                                Text(
                                    text = "Date de sortie: ${movieDetails.release_date}"
                                        ?: "Pas de date de sortie"
                                )
                                Text(
                                    text = "Genres: ${movieDetails.genres.joinToString { it.name }}"
                                        ?: "Aucun genre"
                                )
                                Text(text = "Note: ${movieDetails.vote_average}/10" ?: "N/A")
                            }
                        }
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
                            text = movieDetails.overview ?: "Pas de synopsis",
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
                        // Affichage des acteurs en grille
                        if (movieDetails.credits?.cast != null && movieDetails.credits.cast.isNotEmpty()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {

                                val actors =
                                    movieDetails.credits?.cast?.take(9) // Récupérer les 9 premiers acteurs
                                actors?.let {
                                    for (i in it.indices step 3) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceEvenly
                                        ) {
                                            for (j in 0 until 3) {
                                                if (i + j < it.size) {
                                                    val actor = it[i + j]
                                                    Column(
                                                        horizontalAlignment = Alignment.CenterHorizontally,
                                                        modifier = Modifier
                                                            .padding(4.dp)
                                                            .clickable {
                                                                navController.navigate("acteurDetails/${actor.id}")
                                                            } // Naviguer vers la page de l'acteur
                                                    ) {
                                                        // Image de l'acteur
                                                        AsyncImage(
                                                            model = "https://image.tmdb.org/t/p/w500${actor.profile_path}",
                                                            contentDescription = actor.name,
                                                            modifier = Modifier
                                                                .size(100.dp)
                                                                .clip(CircleShape),
                                                            contentScale = ContentScale.Crop
                                                        )
                                                        // Nom de l'acteur
                                                        Text(
                                                            text = actor.name,
                                                            textAlign = TextAlign.Center,
                                                            modifier = Modifier.padding(top = 4.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            Text(text = "Aucun acteur trouvé.", modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            }
        }

        else -> {
            movie?.let { movieDetails ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Text(
                            text = movieDetails.title,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    item {
                        Row(modifier = Modifier.padding(16.dp)) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500${movieDetails.poster_path}",
                                contentDescription = null,
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(225.dp)
                            )
                            Column(modifier = Modifier.padding(start = 16.dp)) {
                                Text(
                                    text = "Date de sortie: ${movieDetails.release_date}"
                                        ?: "Pas de date de sortie"
                                )
                                Text(
                                    text = "Genres: ${movieDetails.genres.joinToString { it.name }}"
                                        ?: "Aucun genre"
                                )
                                Text(text = "Note: ${movieDetails.vote_average}/10" ?: "N/A")
                            }
                        }
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
                            text = movieDetails.overview ?: "Pas de synopsis",
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
                        // Affichage des acteurs en grille
                        if (movieDetails.credits?.cast != null && movieDetails.credits.cast.isNotEmpty()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {

                                val actors =
                                    movieDetails.credits?.cast?.take(15) // Récupérer les 15 premiers acteurs
                                actors?.let {
                                    for (i in it.indices step 5) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceEvenly
                                        ) {
                                            for (j in 0 until 5) {
                                                if (i + j < it.size) {
                                                    val actor = it[i + j]
                                                    Column(
                                                        horizontalAlignment = Alignment.CenterHorizontally,
                                                        modifier = Modifier
                                                            .padding(4.dp)
                                                            .clickable {
                                                                navController.navigate("acteurDetails/${actor.id}")
                                                            } // Naviguer vers la page de l'acteur
                                                    ) {
                                                        // Image de l'acteur
                                                        AsyncImage(
                                                            model = "https://image.tmdb.org/t/p/w500${actor.profile_path}",
                                                            contentDescription = actor.name,
                                                            modifier = Modifier
                                                                .size(100.dp)
                                                                .clip(CircleShape),
                                                            contentScale = ContentScale.Crop
                                                        )
                                                        // Nom de l'acteur
                                                        Text(
                                                            text = actor.name,
                                                            textAlign = TextAlign.Center,
                                                            modifier = Modifier.padding(top = 4.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            Text(text = "Aucun acteur trouvé.", modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            }
        }
    }
}
