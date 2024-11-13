package com.example.myapptp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapptp1.ui.theme.MyAppTP1Theme
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.window.core.layout.WindowWidthSizeClass

@Serializable
class Home

@Serializable
class Movies

@Serializable
class Series

@Serializable
class Actors

@Serializable
class Collections

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTP1Theme {
                NavigationBar()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBar() {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var showSearch by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            when (windowSizeClass.windowWidthSizeClass) {
                WindowWidthSizeClass.COMPACT -> {
                    // lorsque le telephone est en format paysage on affiche la searchBar
                    if (currentDestination?.hasRoute<Home>() != true) {
                        TopAppBar(
                            title = {
                                if (showSearch) {
                                    SearchBar(
                                        searchText = searchText,
                                        onTextChanged = { query ->
                                            searchText = query
                                            //en fonction de la route où je me trouve je fais une requête pour chercher
                                            when {
                                                currentDestination?.hasRoute<Movies>() == true -> searchMovies(
                                                    query.text,
                                                    viewModel
                                                )

                                                currentDestination?.hasRoute<Series>() == true -> searchSeries(
                                                    query.text,
                                                    viewModel
                                                )

                                                else -> searchActors(query.text, viewModel)
                                            }
                                        }
                                    )
                                } else {
                                        //le titre de mon appli
                                        Text(
                                            text = "Ciné'Verse", color = Color.Yellow,
                                            fontSize = 24.sp, fontWeight = FontWeight.Bold
                                        )
                                }
                            },
                            //afficher ou pas la barre de recherche
                            actions = {
                                SearchButton(
                                    showSearch = showSearch,
                                    onSearchClick = { showSearch = !showSearch }
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(252, 218, 48)
                            ),
                        )
                    }
                }
                else -> {}
            }
        },
        // bouton flottant qui ramène à la page d'accueil
        floatingActionButton = {
            if (currentDestination?.hasRoute<Home>() != true) {
                FloatingActionButton(
                    onClick = { navController.navigate(Home()) },
                    modifier = Modifier
                        .size(60.dp)
                        .padding(end = 16.dp, bottom = 16.dp),
                    containerColor = Color(252, 218, 48),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.house),
                        contentDescription = "home",
                        modifier = Modifier.size(30.dp)
                    )
                }

            }
        },
        //on le place à droite au dessus de la bottomBar
        floatingActionButtonPosition = FabPosition.End,

        bottomBar = {
            //on n'affiche pas la bottomBar si on est sur la page d'accueil
            if (currentDestination?.hasRoute<Home>() != true) {
                when (windowSizeClass.windowWidthSizeClass) {
                    WindowWidthSizeClass.COMPACT -> {
                        NavigationBar(
                            containerColor = Color(252, 218, 48),
                            modifier = Modifier
                                .height(100.dp)
                        ) {
                            //un item pour chaque page (films/séries/acteurs)
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.camera),
                                        contentDescription = "Movie Icon",
                                        modifier = Modifier.size(35.dp),
                                        tint = Color.Black
                                    )
                                },
                                selected = currentDestination?.hasRoute<Movies>() == true,
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Yellow
                                ),
                                onClick = { navController.navigate(Movies()) })
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.series),
                                        contentDescription = "Serie Icon",
                                        modifier = Modifier.size(35.dp),
                                        tint = Color.Black
                                    )
                                },
                                selected = currentDestination?.hasRoute<Series>() == true,
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Yellow
                                ),
                                onClick = { navController.navigate(Series()) })
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.person),
                                        contentDescription = "Actor Icon",
                                        modifier = Modifier.size(35.dp),
                                        tint = Color.Black
                                    )
                                },
                                selected = currentDestination?.hasRoute<Actors>() == true,
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Yellow
                                ),
                                onClick = { navController.navigate(Actors()) })

                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.collection),
                                        contentDescription = "Collection Icon",
                                        modifier = Modifier.size(35.dp),
                                        tint = Color.Black
                                    )
                                },
                                selected = currentDestination?.hasRoute<Collections>() == true,
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Yellow
                                ),
                                onClick = { navController.navigate(Collections()) })
                        }}
                    else -> {}
                }
            }
        })
    { innerPadding ->
        //une Row à 2 column pour afficher la sideBar dans une colonne en mode paysage et le NavHost dans l'autre
        Row {
            if (currentDestination?.hasRoute<Home>() != true) {
                when (windowSizeClass.windowWidthSizeClass) {
                    WindowWidthSizeClass.COMPACT -> {
                    }

                    else -> {
                        Column(modifier = Modifier.padding(innerPadding)) {
                            NavigationBarSide(navController, currentDestination)
                        }
                    }
                }
            }

            Column {
                NavHost(
                    navController = navController, startDestination = Home(),
                    modifier = Modifier.padding(innerPadding)
                ) {
                    //un composable par page qui amène a la fonction {}Screen
                    composable<Home> { HomeScreen(windowSizeClass, navController) }
                    composable<Movies> { MoviesScreen(viewModel, navController, windowSizeClass) }
                    composable<Series> { SeriesScreen(viewModel, navController, windowSizeClass) }
                    composable<Collections> { CollectionsScreen(viewModel, navController, windowSizeClass) }

                    //afficher les détails en fonction de l'id
                    composable(
                        "movieDetails/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getInt("movieId")
                        movieId?.let {
                            MovieDetailsScreen(
                                viewModel = viewModel,
                                movieId = it,
                                navController,
                                windowSizeClass
                            )
                        }
                    }

                    composable(
                        "serieDetails/{serieId}",
                        arguments = listOf(navArgument("serieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val serieId = backStackEntry.arguments?.getInt("serieId")
                        serieId?.let {
                            SerieDetailsScreen(
                                viewModel = viewModel,
                                serieId = it,
                                navController,
                                windowSizeClass
                            )
                        }
                    }

                    composable(
                        "actorDetails/{actorId}",
                        arguments = listOf(navArgument("actorId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val actorId = backStackEntry.arguments?.getInt("actorId")
                        actorId?.let {
                            ActorDetailsScreen(viewModel = viewModel, actorId = it, navController,windowSizeClass)
                        }
                    }
                }
            }
        }
    }
}

//barre de navigation verticale pour le mode paysage
@Composable
fun NavigationBarSide(
    navController: NavController,
    currentDestination: NavDestination?
) {
    NavigationRail(
        modifier = Modifier
            .fillMaxHeight(),
        containerColor = Color(252, 218, 48)
    ) {
        //un item pour chaque page (films/séries/acteurs)
        Spacer(modifier = Modifier.weight(2f))
        NavigationRailItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "Movies",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Black
                )
            },
            selected = currentDestination?.hasRoute<Movies>() == true,
            onClick = { navController.navigate(Movies()) },
            colors = NavigationRailItemDefaults.colors(
                indicatorColor = Color.Yellow
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        NavigationRailItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.series),
                    contentDescription = "Series",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Black
                )
            },
            selected = currentDestination?.hasRoute<Series>() == true,
            onClick = { navController.navigate(Series()) },
            colors = NavigationRailItemDefaults.colors(
                indicatorColor = Color.Yellow
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        NavigationRailItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "Actors",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Black
                )
            },
            selected = currentDestination?.hasRoute<Actors>() == true,
            onClick = { navController.navigate(Actors()) },
            colors = NavigationRailItemDefaults.colors(
                indicatorColor = Color.Yellow
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        NavigationRailItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.collection),
                    contentDescription = "Collections",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Black
                )
            },
            selected = currentDestination?.hasRoute<Collections>() == true,
            onClick = { navController.navigate(Collections()) },
            colors = NavigationRailItemDefaults.colors(
                indicatorColor = Color.Yellow
            )
        )
        Spacer(modifier = Modifier.weight(2f))
        Text(text = "CinéVerse", color = Color.Yellow,fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    placeholderText: String = "Recherche..."
) {
    TextField(
        value = searchText,
        onValueChange = onTextChanged,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(56.dp),
        placeholder = { Text(placeholderText) },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Yellow,
            containerColor = Color(252, 218, 48)
        )
    )
}

@Composable
fun SearchButton(
    showSearch: Boolean,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onSearchClick) {
        Icon(
            painter = painterResource(id = R.drawable.glass),
            modifier = modifier.fillMaxSize(),
            contentDescription = if (showSearch) "Close Search" else "Search"
        )
    }
}

//fonction de recherches
fun searchMovies(query: String, viewModel: MainViewModel) {
    if (query.isNotEmpty()) {
        viewModel.searchMovies(query)
    } else {
        viewModel.getMovies()
    }
}

fun searchSeries(query: String, viewModel: MainViewModel) {
    if (query.isNotEmpty()) {
        viewModel.searchSeries(query)
    } else {
        viewModel.getSeries()
    }
}

fun searchActors(query: String, viewModel: MainViewModel) {
    if (query.isNotEmpty()) {
        viewModel.searchActors(query)
    } else {
        viewModel.getActors()
    }
}
