package com.example.myapptp1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun ActorsScreen(
    viewModel: MainViewModel = viewModel(),
    navController: NavController,
    windowSizeClass: WindowSizeClass
) {
    val actors by viewModel.actors.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Les p'tits Acteurs",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .height(30.dp),
            style = MaterialTheme.typography.headlineSmall

        )

        LaunchedEffect(Unit) {
            viewModel.getActors()
        }

        when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> {
                LazyVerticalGrid(

                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(16.dp),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(actors) { actor ->
                        ActorItem(actor = actor, navController = navController, windowSizeClass)
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
                    items(actors) { actor ->
                        ActorItem(actor = actor, navController = navController, windowSizeClass)
                    }
                }
            }
        }


    }
}

@Composable
fun ActorItem(actor: ModelActor, navController: NavController, windowSizeClass: WindowSizeClass) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            navController.navigate("actorDetails/${actor.id}")
        }) {

        if (actor.profile_path == null) {
            Text(
                text = "Chargement de l'image non disponible...",
                modifier = Modifier.padding(top = 4.dp)
            )
        } else {
            when (windowSizeClass.windowWidthSizeClass) {
                WindowWidthSizeClass.COMPACT -> {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${actor.profile_path}",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }

                else -> {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${actor.profile_path}",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(190.dp)
                    )
                }
            }
        }

        Text(
            text = actor.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )


    }
}



