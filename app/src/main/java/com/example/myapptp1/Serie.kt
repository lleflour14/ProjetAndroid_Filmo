package com.example.myapptp1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun SerieScreen(viewModel: MainViewModel = viewModel(), navController: NavController) {

    val series by viewModel.series.collectAsState()
    Column(
        modifier = Modifier.padding(16.dp) // Applique un padding global à la colonne
    ) {
        Text(
            text = "Les p'tites Series",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.headlineSmall

        )
        // Charger les films au chargement de l'écran
        LaunchedEffect(Unit) {
            viewModel.getSeries()  // Appelle la fonction pour récupérer les films
        }

        LazyVerticalGrid(

            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(16.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(series) { serie ->
                SerieItem(serie = serie, navController = navController)
            }


        }
    }
}

@Composable
fun SerieItem(serie: ResultTV, navController: NavController) {

    // Composant qui affiche les détails d'un film
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            navController.navigate("serieDetails/${serie.id}")
        }
    ) {

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${serie.poster_path}", // Lien de l'image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        // Afficher le titre en gras
        Text(
            text = serie.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Afficher la date de sortie en dessous du titre
        Text(
            text = "Date de sortie: ${serie.first_air_date}",
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}



