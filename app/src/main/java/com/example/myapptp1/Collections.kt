package com.example.myapptp1

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass

@Composable
fun CollectionsScreen(viewModel: MainViewModel,
                      navController: NavController,
                      windowSizeClass: WindowSizeClass
){
    Text(text = "Collections")
}