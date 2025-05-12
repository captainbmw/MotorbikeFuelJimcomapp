package com.bmw.motorbikefueljimcomapp.ui.theme.screens.About



import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.R
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_DASHBOARD
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_OWNER
import com.bmw.motorbikefueljimcomapp.ui.theme.MotorbikeFuelJimcomAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("About Page",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary) },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true, // Mark this item as selected for the dashboard
                    onClick = { navController.navigate(ROUTE_HOME) },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Dashboard") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUTE_OWNER) },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                    label = { Text("Owner") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUTE_DASHBOARD)},
                    icon = { Icon(Icons.Filled.Menu, contentDescription = "Settings") },
                    label = { Text("Dashboard") }
                )
            }
        }

    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
                .paint(
                    painter = painterResource(R.drawable.bike7),
                    contentScale = ContentScale.FillBounds
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "About Motorbike Fuel Jimcom App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "This application helps you manage your motorbike fuel consumption and track your rides.",
                fontSize = 16.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Version: 1.0.0",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Developed by: Jimcom",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
//            Card(
//                modifier = Modifier
//                    .width(100.dp)
//                    .height(100.dp)
//                    .clickable { navController.navigate(ROUTE_HOME) },
//                shape = CircleShape,
//
//
//                elevation = CardDefaults.cardElevation(10.dp)
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//
//                    Image(
//                        painter = painterResource(com.bmw.motorbikefueljimcomapp.R.drawable.home),
//                        contentDescription = "home",
//                        modifier = Modifier.height(70.dp).fillMaxSize()
//
//                    )
//
//                    Text(
//                        text = "Home",
//                        fontSize = 15.sp
//
//                    )
//                }
//
//            }
            // You can add more information here, like contact details, website, etc.

            Spacer(modifier = Modifier.height(24.dp))

//            Button(onClick = { navController.popBackStack() }) {
//                Text("Go Back")
//            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    MotorbikeFuelJimcomAppTheme {
        AboutScreen(rememberNavController())
    }
}