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
fun ActorsScreen(
    viewModel: MainViewModel,
    navController: NavController,
    windowSizeClass: WindowSizeClass
) {
    val actors by viewModel.actors.collectAsState()

    MediaScreen(
        title = "Les p'tits acteurs",
        items = actors,
        onGetItems = { viewModel.getActors() },
        onSearchItems = { viewModel.searchActors(it) },
        windowSizeClass = windowSizeClass
    ) { actor -> ActorItem(actor, navController, windowSizeClass) }
}


@Composable
fun ActorItem(actor: ModelActor, navController: NavController, windowSizeClass: WindowSizeClass) {
    MediaItem(
        item = actor,
        navController = navController,
        windowSizeClass = windowSizeClass,
        pictureUrl = "https://image.tmdb.org/t/p/w500${actor.profile_path}",
        title = actor.name,
        releaseDate = null.toString(),
        destinationRoute = "actorDetails/${actor.id}"
    )

}

@Composable
fun ActorDetailsScreen(
    viewModel: MainViewModel,
    actorId: Int,
    navController: NavController,
    windowSizeClass: WindowSizeClass
) {
    LaunchedEffect(actorId) {
        viewModel.getActorById(actorId)
    }

    val actor by viewModel.selectedActor.collectAsState(initial = null)

    actor?.let { actorDetails ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                MediaDetailsHeader(
                    pictureUrlBack = null,
                    pic = actorDetails.profile_path,
                    title = actorDetails.name,
                    releaseDateOrBirth = actorDetails.birthday,
                    genresOrPlace = actorDetails.place_of_birth,
                    voteAverage = 0.0,
                    windowSizeClass = windowSizeClass,
                    mediaType = "Actor"
                )
            }
            item {
                Text(
                    text = "Biographie",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item {
                if (actorDetails.biography.isEmpty()) {
                    Text(
                        text = "Pas de biographie",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    Text(
                        text = actorDetails.biography,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            item {
                Text(
                    text = "Films principaux",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item {
                MediaDetailsCredits(
                    items = actorDetails.credits?.cast?.take(9) ?: emptyList(),
                    navController = navController,
                    route = "movieDetails"
                )
            }
        }
    }
}
