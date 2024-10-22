package com.example.myapptp1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

class DisplayCard {

   /*
@Composable
fun ScreenCard(itemsList : List<>,viewModel: MainViewModel,navController: NavController,windowClass: WindowSizeClass) {
        var searchText by remember { mutableStateOf(TextFieldValue("")) }
        var showSearch by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Chargement
            LaunchedEffect(Unit) {
                viewModel.getMovies() //????????????????
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = "Les p'tits Films", //?????????
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    style = MaterialTheme.typography.headlineSmall,
                )
                when (windowClass.windowWidthSizeClass) {
                    WindowWidthSizeClass.COMPACT -> {}
                    else -> {
                        SearchBar(
                            searchText = searchText,
                            onTextChanged = { query ->
                                searchText = query
                                if (query.text.isNotEmpty()) {
                                    viewModel.searchMovies(query.text) //?????????
                                } else {
                                    viewModel.getMovies() //???????????
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.glass),
                            contentDescription = "Glass Icon",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    }

                }}
            when (windowClass.windowWidthSizeClass) {
                WindowWidthSizeClass.COMPACT -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.padding(16.dp),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(itemsList) { item ->
                            MovieItem(item, navController = navController, windowClass)
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
                        items(itemsList) { item ->
                            MovieItem(item, navController = navController, windowClass)
                        }
                    }
                }
            }

        }
    }

    @Composable
    fun MovieItem(item: , navController: NavController, windowSizeClass: WindowSizeClass) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("movieDetails/${item.id}") //????????????
            }
        ) {

            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${item.poster_path}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "Date de sortie: ${item.release_date}",
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }

*/

}