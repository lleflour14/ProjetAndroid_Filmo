package com.example.myapptp1


import android.util.Log
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
import coil.compose.AsyncImage

@Composable
fun SerieDetailsScreen(viewModel: MainViewModel, serieId: Int, navController: NavController) {
    // Appeler la fonction pour récupérer les détails du film
    LaunchedEffect(serieId) {
        viewModel.getSerieById(serieId)
    }

    val serie by viewModel.selectedSerie.collectAsState(initial = null)
    Log.d("SerieDetailsScreen", "Serie ID: $serieId, Serie: $serie")

    serie?.let { serieDetails ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${serieDetails.backdrop_path}",
                    contentDescription = "Image de la série",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }
            item {
                Text(
                    text = serieDetails.name ?: "Nom non disponible",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                Row(modifier = Modifier.padding(16.dp)) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${serieDetails.poster_path}",
                        contentDescription = "poster de la série",
                        modifier = Modifier
                            .width(150.dp)
                            .height(225.dp)
                    )
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        Text(text = "Date de sortie: ${serieDetails.last_air_date}" ?: "Pas de date de sortie")
                        Text(text = "Genres: ${serieDetails.genres?.joinToString(", ") { genre -> genre.name } ?: "Aucun genre"}") // Valeur par défaut si null
                        Text(text = "Note: ${serieDetails.vote_average}/10" ?: "N/A")
                    }
                }
            }
            item{
                Text(
                    text = "Synopsis",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )}
            item{
                Text(
                    text = serieDetails.overview ?: "Aucun synopsis disponible.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp)
                )}
            item{
                Text(
                    text = "Acteurs principaux",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )}
            item{
                // Affichage des acteurs en grille
                if (!serieDetails.credits?.cast.isNullOrEmpty()) {
                    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        val actors = serieDetails.credits?.cast?.take(9) // Récupérer les 9 premiers acteurs
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
                                                    .weight(1f)
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