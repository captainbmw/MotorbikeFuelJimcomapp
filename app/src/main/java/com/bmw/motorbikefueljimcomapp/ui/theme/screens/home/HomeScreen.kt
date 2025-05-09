package com.bmw.motorbikefueljimcomapp.ui.theme.screens.home




import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.R
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOAN
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOGIN
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_MOTORBIKE
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_OWNER
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_REGISTER
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_REPAYMENT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,


) {


    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "Motorbike Fuel Jimcom App",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Blue,
                    modifier = Modifier.padding(start = 16.dp),
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(ROUTE_OWNER) }) {
                Icon(Icons.Filled.Add, contentDescription = "Add New Owner")
            }
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                NavigationBarItem(onClick = { navController.navigate(ROUTE_LOGIN)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Add New Owner") },
                    label = { Text("Home") },
                    selected = false)
                NavigationBarItem(onClick = { navController.navigate(ROUTE_LOGIN)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Login") },
                    label = { Text("Login") },
                    selected = false)

                NavigationBarItem(onClick = { navController.navigate(ROUTE_REGISTER)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Register") },
                    label = { Text("Register") },
                    selected = false)

            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .paint(
                    painter = painterResource(R.drawable.bike3),
                    contentScale = ContentScale.FillBounds
                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Welcome to Motorbike Fuel Jimcom App",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                SummaryCard(
                    title = "Owners",
                    onClick = { navController.navigate(ROUTE_OWNER) },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                SummaryCard(
                    title = "Motorbikes",
                    onClick = { navController.navigate(ROUTE_MOTORBIKE) },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                SummaryCard(
                    title = "Loans",
                    onClick = { navController.navigate(ROUTE_LOAN) },
                    modifier = Modifier.weight(1f))
            }




            Text(
                "Quick Actions",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Button(
                onClick = { navController.navigate(ROUTE_OWNER) },
                modifier = Modifier
                    .width(300.dp)
                    .padding(bottom = 16.dp)
            ) {
                Text("Add Owner")
            }
            Button(
                onClick = { navController.navigate(ROUTE_MOTORBIKE) },
                modifier = Modifier
                    .width(300.dp)
                    .padding(bottom = 16.dp)
            ) {
                Text("Add Motorbike")
            }
            Button(
                onClick = { navController.navigate(ROUTE_LOAN) },
                modifier = Modifier
                    .width(300.dp)
                    .padding(bottom = 16.dp)
            ) {
                Text("Apply Loan")
            }

        }
    }


}





@Composable
fun SummaryCard(title: String,  onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = Color.Blue)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
    
}