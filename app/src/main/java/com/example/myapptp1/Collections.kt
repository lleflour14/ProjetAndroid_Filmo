package com.example.myapptp1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CollectionsScreen(
    viewModel: MainViewModel,
    navController: NavController,
) {
    val collections by viewModel.collections.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.searchCollections()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    )

    {
        items(collections) { collect ->
            CollectItem(collect = collect, navController)
        }
    }

}


@Composable
fun CollectItem(collect: ModelCollection, navController: NavController) {

    Column(modifier = Modifier.padding(16.dp)) {
        if (collect.poster_path != null) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${collect.poster_path}",
                contentDescription = collect.name,
                modifier = Modifier
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
        Text(
            text = collect.name,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.Black, // Couleur du texte
            modifier = Modifier
                .padding(top = 8.dp)
                .background(Color(252, 218, 48), RoundedCornerShape(8.dp))
                .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                .padding(8.dp)         )
    }


}