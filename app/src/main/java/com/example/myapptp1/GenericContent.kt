package com.example.myapptp1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun <T> MediaScreen(
    title: String,
    items: List<T>,
    onGetItems: () -> Unit,
    onSearchItems: (String) -> Unit,
    navController: NavController,
    windowSizeClass: WindowSizeClass,
    itemContent: @Composable (T) -> Unit
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            if (windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT) {
                SearchBar(
                    searchText = searchText,
                    onTextChanged = { query ->
                        searchText = query
                        if (query.text.isNotEmpty()) onSearchItems(query.text) else onGetItems()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                )
            }
        }

        LaunchedEffect(Unit) { onGetItems() }

        LazyVerticalGrid(
            columns = GridCells.Fixed(if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) 2 else 4),
            modifier = Modifier.padding(16.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { item -> itemContent(item) }
        }
    }
}

@Composable
fun <T> MediaItem(
    item: T,
    navController: NavController,
    windowSizeClass: WindowSizeClass,
    pictureUrl: String,
    title: String,
    releaseDate: String,
    destinationRoute: String
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            navController.navigate(destinationRoute)
        }) {
        AsyncImage(
            model = pictureUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) 200.dp else 190.dp)
        )
        Text(text = title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
        if(releaseDate != null.toString()) {
            Text(
                text = "Date de sortie : $releaseDate",
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


@Composable
fun MediaDetailsHeader(
    pictureUrlBack: String?,
    pic: String?,
    title: String,
    releaseDateOrBirth: String?,
    genresOrPlace: String?,
    voteAverage: Double,
    windowSizeClass: WindowSizeClass,
    mediaType: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> {
                if(mediaType != "Actor"){
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500$pictureUrlBack",
                    contentDescription = "Image principale",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )}
            }else->{}}
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Row(modifier = Modifier.padding(16.dp)) {
                if (pic == null) {
                    Icon(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "user Icon",
                        modifier = Modifier.size(150.dp)
                    )
                } else {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500$pic",
                        contentDescription = "Image principale",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(150.dp)
                            .height(225.dp)
                    )
                }
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    if(mediaType == "Actor"){
                        if ( releaseDateOrBirth == null && genresOrPlace == null) {
                            Text(
                                text = "Pas d'informations sur la naissance"
                            )
                        } else {
                            Text(
                                text = "Né(é) le : ${releaseDateOrBirth}"
                            )
                            Text(
                                text = "à : ${genresOrPlace}"
                            )
                        }
                   }else{
                        Text(text = "Date de sortie: ${releaseDateOrBirth ?: "Inconnue"}")
                        Text(text = "Genres: ${genresOrPlace ?: "Inconnus"}")
                        Text(text = "Note: ${voteAverage ?: "Non noté"}/10")
                   }
                }
            }


    }
}

@Composable
fun MediaDetailsCast(
    items: List<Cast>,
    navController: NavController,
    route: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        items.chunked(3).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowItems.forEach { item ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1f)
                                .clickable {
                                    navController.navigate("$route/${item.id}")
                                }
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500${item.profile_path}",
                                contentDescription = item.name,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }

        }
    }
}


@Composable
fun MediaDetailsCredits(
    items: List<CastActors>,
    navController: NavController,
    route: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        items.chunked(3).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowItems.forEach { item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f)
                            .clickable {
                                navController.navigate("$route/${item.id}")
                            }
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${item.poster_path}",
                            contentDescription = item.title,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = item.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

        }
    }
}


