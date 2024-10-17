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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
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
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Serializable
class Home

@Serializable
class Film

@Serializable
class Serie

@Serializable
class Acteur

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTP1Theme {
                val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
                when (windowSizeClass.windowWidthSizeClass) {
                    WindowWidthSizeClass.COMPACT -> {}else->{}}

                NavigationBarre(windowSizeClass)


            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBarre(windowSizeClass: WindowSizeClass) {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var showSearch by remember { mutableStateOf(false) } // Gérer l'état de la barre de recherche
    var searchText by remember { mutableStateOf(TextFieldValue("")) } // Texte de la recherche

    Scaffold(
        topBar = {
            when (windowSizeClass.windowWidthSizeClass) {

                WindowWidthSizeClass.COMPACT -> {

                    if (currentDestination?.hasRoute<Home>() != true) {
                        TopAppBar(
                            title = {
                                if (showSearch) {
                                    TextField(
                                        value = searchText,
                                        onValueChange = { query ->
                                            searchText = query
                                            if (currentDestination?.hasRoute<Film>() == true) {

                                                if (query.text.isNotEmpty()) {
                                                    viewModel.searchMovies(query.text)
                                                } else {
                                                    viewModel.getMovies()
                                                }

                                            }
                                            if (currentDestination?.hasRoute<Serie>() == true) {

                                                if (query.text.isNotEmpty()) {
                                                    viewModel.searchSeries(query.text)
                                                } else {
                                                    viewModel.getSeries()
                                                }

                                            } else {
                                                if (query.text.isNotEmpty()) {
                                                    viewModel.searchActeurs(query.text)
                                                } else {
                                                    viewModel.getActeurs()
                                                }

                                            }
                                        },
                                        singleLine = true,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp)
                                            .height(56.dp),
                                        placeholder = { Text("Recherche...") },
                                        colors = TextFieldDefaults.textFieldColors(
                                            cursorColor = Color.White,
                                            containerColor = Color(
                                                252,
                                                218,
                                                48
                                            ) // Couleur de la zone de recherche
                                        )
                                    )
                                } else {

                                    Text(text = "Bizar'App", modifier = Modifier.fillMaxWidth())
                                }

                            },
                            actions = {
                                IconButton(onClick = { showSearch = !showSearch }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.loupe),
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentDescription = "Recherche"

                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(),

                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(
                                    252, 218, 48
                                )
                            ),


                            )
                    }
                }

                else -> {}
            }
        },
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
                        painter = painterResource(id = R.drawable.maison_ic), // Remplace avec l'image de ta maison
                        contentDescription = "Retour à l'accueil",
                        modifier = Modifier.size(30.dp) // Ajuste la taille de l'icône
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,

        bottomBar = {
            if (currentDestination?.hasRoute<Home>() != true) {
                when (windowSizeClass.windowWidthSizeClass) {
                    WindowWidthSizeClass.COMPACT -> {

                        NavigationBar(
                            containerColor = Color(252, 218, 48),
                            modifier = Modifier
                                .height(100.dp)
                        ) {
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.camera_video),
                                        contentDescription = "Movie Icon",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.Black
                                    )
                                }, label = { Text("Films") },
                                selected = currentDestination?.hasRoute<Film>() == true,
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Yellow
                                ),
                                onClick = { navController.navigate(Film()) })
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.series),
                                        contentDescription = "Serie Icon",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.Black
                                    )
                                }, label = { Text("Séries") },
                                selected = currentDestination?.hasRoute<Serie>() == true,
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Yellow
                                ),
                                onClick = { navController.navigate(Serie()) })
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.la_personne),
                                        contentDescription = "Actor Icon",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.Black
                                    )
                                }, label = { Text("Acteurs") },
                                selected = currentDestination?.hasRoute<Acteur>() == true,
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Yellow
                                ),
                                onClick = { navController.navigate(Acteur()) })
                        }
                    }

                    else -> {}
                }
            }

        })
    { innerPadding ->

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
                    composable<Home> { HomeScreen(windowSizeClass, navController) }
                    composable<Film> { FilmScreen(viewModel, navController, windowSizeClass) }
                    composable<Serie> { SerieScreen(viewModel, navController) }
                    composable<Acteur> { ActeurScreen(viewModel, navController) }

                    composable(
                        "movieDetails/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getInt("movieId")
                        movieId?.let {
                            FilmDetailsScreen(
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
                            SerieDetailsScreen(viewModel = viewModel, serieId = it, navController)
                        }
                    }

                    composable(
                        "acteurDetails/{actorId}",
                        arguments = listOf(navArgument("actorId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val actorId = backStackEntry.arguments?.getInt("actorId")
                        actorId?.let {
                            ActorDetailsScreen(viewModel = viewModel, actorId = it, navController)
                        }
                    }
                }
            }
        }
    }
}


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
        Spacer(modifier = Modifier.weight(2f))
        NavigationRailItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.camera_video),
                    contentDescription = "Films",
                    modifier = Modifier.size(40.dp), // Taille de l'icône augmentée
                    tint = Color.Black
                )
            },
            selected = currentDestination?.hasRoute<Film>() == true,
            onClick = { navController.navigate(Film()) },
            colors = NavigationRailItemDefaults.colors(
                selectedIconColor = Color.Black, // Couleur surlignée si sélectionnée
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        NavigationRailItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.series),
                    contentDescription = "Séries",
                    modifier = Modifier.size(40.dp), // Taille de l'icône augmentée
                    tint = Color.Black
                )
            },
            selected = currentDestination?.hasRoute<Serie>() == true,
            onClick = { navController.navigate(Serie()) },
            colors = NavigationRailItemDefaults.colors(
                selectedIconColor = Color.Yellow, // Couleur surlignée si sélectionnée
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        NavigationRailItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.la_personne),
                    contentDescription = "Acteurs",
                    modifier = Modifier.size(40.dp), // Taille de l'icône augmentée
                    tint = Color.Black
                )
            },
            selected = currentDestination?.hasRoute<Acteur>() == true,
            onClick = { navController.navigate(Acteur()) },
            colors = NavigationRailItemDefaults.colors(
                selectedIconColor = Color.Yellow, // Couleur surlignée si sélectionnée
            )
        )
        Spacer(modifier = Modifier.weight(2f))
    }

}

