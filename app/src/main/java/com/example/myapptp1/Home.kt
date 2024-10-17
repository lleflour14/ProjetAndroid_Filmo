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
fun HomeScreen(windowClass: WindowSizeClass, navController: NavHostController) {
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
                Picture()
                Spacer(modifier = Modifier.height(16.dp))
                Data()
                Spacer(modifier = Modifier.height(32.dp))
                Network()
                Spacer(modifier = Modifier.height(65.dp))
                ButtonStart(navController)
            }
        }

        else -> {
            Row(
                modifier = Modifier
                    .padding(40.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Picture()
                    Spacer(modifier = Modifier.height(10.dp))
                    Data()
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Network()
                    Spacer(modifier = Modifier.height(15.dp))
                    ButtonStart(navController)
                }
            }
        }
    }
}

@Composable
fun Picture() {
    Image(
        painter = painterResource(id = R.drawable.profil_picture),
        contentDescription = "profilPicture",
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .border(
                BorderStroke(2.dp, Color.Gray),
                CircleShape
            ),
        contentScale = ContentScale.Crop
    )

}

@Composable
fun Data() {
    Text(
        text = "Louise Le Flour",
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Etudiante",
        fontStyle = FontStyle.Italic,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center
    )

    Text(
        text = "Ecole d'ingénieur ISIS - INU Champollion",
        fontStyle = FontStyle.Italic,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )

}

@Composable
fun Network() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.align(Alignment.Start)
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.ic_mail),
                contentDescription = "LinkedIn Icon",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "louise.le_flour@etud.univ-jfc.fr",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_black_linkedin),
                contentDescription = "LinkedIn Icon",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "www.linkedin.com/in/louise-leflour",
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Composable
fun ButtonStart(navController: NavHostController) {
    Button(
        onClick = { navController.navigate(Movies()) },
        colors = ButtonDefaults.buttonColors(containerColor = Color(252, 218, 48))
    ) {
        Text(text = "Démarrer", color = Color.Black)
    }
}


