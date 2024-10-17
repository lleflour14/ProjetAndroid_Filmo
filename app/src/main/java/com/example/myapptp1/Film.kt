package com.example.myapptp1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun FilmScreen(
    viewModel: MainViewModel,
    navController: NavController,
    windowClass: WindowSizeClass
) {

    val movies by viewModel.movies.collectAsState()

    var query by remember { mutableStateOf("") }



    Column(
        modifier = Modifier.padding(16.dp) // Applique un padding global à la colonne
    ) {
        Text(
            text = "Les p'tits Films",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp),
            style = MaterialTheme.typography.headlineSmall


        )

        // Charger les films au chargement de l'écran
        LaunchedEffect(Unit) {
            viewModel.getMovies()  // Appelle la fonction pour récupérer les films
        }
        when (windowClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(16.dp),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(movies) { movie ->
                        MovieItem(movie = movie, navController = navController,windowClass)
                    }
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.padding(16.dp),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(movies) { movie ->
                        MovieItem(movie = movie, navController = navController,windowClass)
                    }
                }
            }
        }


    }
}

@Composable
fun MovieItem(movie: ResultMovie, navController: NavController,windowSizeClass: WindowSizeClass) {
    // Composant qui affiche les détails d'un film
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            navController.navigate("movieDetails/${movie.id}")
        }
    ) {
        when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}", // Lien de l'image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(250.dp)
        )
            }else->{ AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}", // Lien de l'image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(200.dp)
        )}}
        // Afficher le titre en gras
        Text(
            text = movie.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Afficher la date de sortie en dessous du titre
        Text(
            text = "Date de sortie: ${movie.release_date}",
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

