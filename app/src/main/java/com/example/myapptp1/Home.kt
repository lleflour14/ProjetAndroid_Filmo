package com.example.myapptp1


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun HomeScreen(windowClass: WindowSizeClass,navController: NavHostController) {
    when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            // centrés verticalement
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Photo()
                Spacer(modifier = Modifier.height(16.dp))
                Infos()
                Spacer(modifier = Modifier.height(32.dp))
                Reseaux()
                Spacer(modifier = Modifier.height(65.dp))
                Bouton(navController)
            }
        }
        else -> {
            Row(modifier = Modifier
                .padding(40.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Photo()
                    Spacer(modifier = Modifier.height(10.dp))
                    Infos()
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Reseaux()
                    Spacer(modifier = Modifier.height(15.dp))
                    Bouton(navController)
                }
            }
        }
    }
}

@Composable
fun Photo(){
    // Encapsuler l'image dans un Box pour ajouter une bordure

        // Ajouter une image en haut
        Image(
            painter = painterResource(id = R.drawable._0_moiphoto),
            contentDescription = "Photo de profil",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(
                    BorderStroke(2.dp, Color.Gray), // Bordure de 2dp et couleur grise
                    CircleShape
                ), // Donne une forme circulaire à l'image
            contentScale = ContentScale.Crop
        )

}

@Composable
fun Infos(){
    // Ajouter nom et prénom en gras et en gros
    Text(
        text = "Louise Le Flour",
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Ajouter statut
    Text(
        text = "Etudiante",
        fontStyle = FontStyle.Italic,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center
    )

    // Ajouter école en italique
    Text(
        text = "Ecole d'ingénieur ISIS - INU Champollion",
        fontStyle = FontStyle.Italic,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )

}

@Composable
fun Reseaux(){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        // Ajouter email et LinkedIn avec icônes
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.align(Alignment.Start)
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.ic_mail), // Remplacez avec votre icône LinkedIn
                contentDescription = "LinkedIn Icon",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "louise.le_flour@etud.univ-jfc.fr",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Row(
            //verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_black_linkedin), // Remplacez avec votre icône LinkedIn
                contentDescription = "LinkedIn Icon",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "www.linkedin.com/in/louise-leflour",
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = TextDecoration.Underline // pour souligner
            )
        }
    }
}

@Composable
fun Bouton(navController: NavHostController){
    // Ajouter un bouton en bas
    Button(
        onClick = {navController.navigate(Film()) },
        colors = ButtonDefaults.buttonColors(containerColor = Color(252, 218, 48))
    ) {
        Text(text = "Démarrer", color = Color.Black)
    }
}


