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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ActorDetailsScreen(viewModel: MainViewModel, actorId: Int, navController: NavController) {

    LaunchedEffect(actorId) {
        viewModel.getActorById(actorId)
    }

    val actor by viewModel.selectedActor.collectAsState()

    actor?.let { actorDetails ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            item {
                Text(
                    text = actorDetails.name,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {

                Row(modifier = Modifier.padding(16.dp)) {
                    if (actorDetails.profile_path == null) {
                        Icon(
                            painter = painterResource(id = R.drawable.utilisateur_ic), // Remplacez avec votre icône LinkedIn
                            contentDescription = "user Icon",
                            modifier = Modifier.size(150.dp)
                        )
                    } else {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${actorDetails.profile_path}",
                            contentDescription = null,
                            modifier = Modifier
                                .width(150.dp)
                                .height(225.dp)
                        )
                    }
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        if (actorDetails.birthday == null && actorDetails.place_of_birth == null) {
                            Text(
                                text = "Pas d'informations sur la naissance"
                            )
                        } else {
                            Text(
                                text = "Né(é) le : ${actorDetails.birthday}"
                            )
                            Text(
                                text = "à : ${actorDetails.place_of_birth}"
                            )
                        }
                    }
                }

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
                    text = "Filmographie",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item {
                // Affichage des acteurs en grille
                if (actorDetails.credits?.cast != null && actorDetails.credits.cast.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {

                            val movie =
                                actorDetails.credits.cast.take(9) // Récupérer les 9 premiers films
                            movie.let {
                                for (i in it.indices step 3) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        for (j in 0 until 3) {
                                            if (i + j < it.size) {
                                                val movie = it[i + j]
                                                Column(
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    modifier = Modifier
                                                        .padding(4.dp)
                                                        .weight(1f)
                                                        .clickable {
                                                            navController.navigate("movieDetails/${movie.id}")
                                                        } // Naviguer vers la page de l'acteur
                                                ) {
                                                    // Image du films
                                                    if (movie.poster_path == null) {

                                                            Icon(
                                                                painterResource(id = R.drawable.camera_video),
                                                                contentDescription = "Films"
                                                            )

                                                    }else{
                                                    AsyncImage(

                                                        model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                                                        contentDescription = movie.title,
                                                        modifier = Modifier
                                                            .size(100.dp)
                                                            .clip(CircleShape),
                                                        contentScale = ContentScale.Crop
                                                    )}
                                                    // Nom de l'acteur
                                                    Text(
                                                        text = movie.title,
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
                    }
                } else {
                    Text(text = "Aucun films trouvé.", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}