package com.example.myapptp1

import android.text.Layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import java.lang.reflect.Modifier

@Composable
fun CollectionsScreen(
    viewModel: MainViewModel,
    navController: NavController,
    windowSizeClass: WindowSizeClass
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
    Column(
    ) {

        Text(text = collect.name)
    }
}