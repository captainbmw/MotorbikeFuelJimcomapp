package com.bmw.motorbikefueljimcomapp.ui.theme.screens.Dashboard



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.R
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_ABOUT
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_INSURANCE
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOAN
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOGIN
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_OWNER
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_REPAYMENT
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_UPDATE
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_VIEW_LOAN
import com.bmw.motorbikefueljimcomapp.ui.theme.MotorbikeFuelJimcomAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Dashboard",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 45.sp,
                textAlign = TextAlign.Center) },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                })
        },
        bottomBar = {
//            NavigationBar {
//                NavigationBarItem(
//                    selected = true, // Mark this item as selected for the dashboard
//                    onClick = { navController.navigate(ROUTE_HOME) },
//                    icon = { Icon(Icons.Filled.Home, contentDescription = "Dashboard") },
//                    label = { Text("Home") }
//                )
//                NavigationBarItem(
//                    selected = false,
//                    onClick = { /* Navigate to another screen */ },
//                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
//                    label = { Text("") }
//                )
//                NavigationBarItem(
//                    selected = false,
//                    onClick = { /* Navigate to another screen */ },
//                    icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
//                    label = { Text("Settings") }
//                )
//            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text("Welcome to  Dashboard!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 30.sp)
            // Add more Composable elements here to build your dashboard UI
            // e.g., cards, charts, lists, etc.
            //SearchBar
            var search by remember { mutableStateOf("") }
            OutlinedTextField(
                value = search,
                onValueChange = {search = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(start = 20.dp, end = 20.dp)
                    .verticalScroll(rememberScrollState()),
                leadingIcon = { Icon(imageVector =Icons.Default.Search, contentDescription = "")},
                placeholder = { Text(text = "search...",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold)}
            )
            Spacer(modifier = Modifier.height(20.dp))
            //Row1 (start)

            Row(modifier = Modifier

                .padding(start = 20.dp),

                ) {
                //Card1(start)
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clickable { navController.navigate(ROUTE_HOME) },
                    shape = RectangleShape,


                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            painter = painterResource(com.bmw.motorbikefueljimcomapp.R.drawable.home),
                            contentDescription = "home",
                            modifier = Modifier.height(70.dp).fillMaxSize()

                        )

                        Text(
                            text = "Home",
                            fontSize = 15.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold

                        )
                    }

                }

                //Card1(end)
                Spacer(
                    modifier = Modifier
                        .width(25.dp)
                )

                //Card2(start)
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clickable { navController.navigate(ROUTE_LOAN) },
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(com.bmw.motorbikefueljimcomapp.R.drawable.loan),
                            contentDescription = "about",
                            modifier = Modifier.height(70.dp).fillMaxSize()

                        )
                        Text(
                            text = "LOAN",
                            fontSize = 15.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold

                        )
                    }

                }

                //Card2(end)
                Spacer(
                    modifier = Modifier
                        .width(25.dp)
                )

                //Card2(start)
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clickable { navController.navigate(ROUTE_OWNER) },
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            painter = painterResource(com.bmw.motorbikefueljimcomapp.R.drawable.owner),
                            contentDescription = "about",
                            modifier = Modifier.height(60.dp)

                        )
                        Text(
                            text = "Owner",
                            fontSize = 15.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold

                        )
                    }

                }

                //Card2(end)

            }
            Spacer(modifier = Modifier.height(20.dp))

            //Row2 (start)
            Row(modifier = Modifier.padding(start = 20.dp)) {
                //Card1(start)
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clickable { navController.navigate(ROUTE_REPAYMENT) },
                    shape = RectangleShape,
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            painter = painterResource(com.bmw.motorbikefueljimcomapp.R.drawable.repayment),
                            contentDescription = "repayment",
                            modifier = Modifier.height(60.dp)

                        )
                        Text(
                            text = "Repayment",
                            fontSize = 15.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold

                        )
                    }

                }

                //Card1(end)
                Spacer(modifier = Modifier.width(25.dp))

                //Card2(start)
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clickable { navController.navigate(ROUTE_INSURANCE) },
                    shape = RectangleShape,
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            painter = painterResource(com.bmw.motorbikefueljimcomapp.R.drawable.insurance),
                            contentDescription = "items",
                            modifier = Modifier.height(60.dp)

                        )
                        Text(
                            text = "Insurance",
                            fontSize = 15.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold

                        )
                    }

                }

                //Card2(end)
                Spacer(modifier = Modifier.width(25.dp))

                //Card2(start)
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clickable { navController.navigate(ROUTE_VIEW_LOAN) },
                    shape = RectangleShape,
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        Image(
                            painter = painterResource(com.bmw.motorbikefueljimcomapp.R.drawable.view),
                            contentDescription = "items",
                            modifier = Modifier.height(70.dp).fillMaxSize()

                        )
                        Text(
                            text = "View Loans",
                            fontSize = 15.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold

                        )
                    }

                }

                //Card2(end)

            }
            Spacer(modifier = Modifier.height(30.dp))

            //Row (start)
            Row(modifier = Modifier.padding(start = 20.dp, bottom = 20.dp)) {
                //Card1(start)
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clickable { navController.navigate(ROUTE_UPDATE) },
                    shape = RectangleShape,


                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            painter = painterResource(com.bmw.motorbikefueljimcomapp.R.drawable.update),
                            contentDescription = "AI",
                            modifier = Modifier.height(70.dp).fillMaxSize()

                        )
                        Text(
                            text = "Update Loan",
                            fontSize = 15.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold

                        )
                    }

                }

                //Card1(end)
                Spacer(
                    modifier = Modifier
                        .width(35.dp)
                )

                //Card2(start)
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clickable { navController.navigate(ROUTE_ABOUT) },
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            painter = painterResource(com.bmw.motorbikefueljimcomapp.R.drawable.about),
                            contentDescription = "items",
                            modifier = Modifier.height(70.dp).fillMaxSize()

                        )
                        Text(
                            text = "About",
                            fontSize = 15.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold

                        )
                    }

                }

                //Card2(end)
                Spacer(
                    modifier = Modifier
                        .width(35.dp)
                )

                //Card2(start)
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clickable { navController.navigate(ROUTE_LOGIN) },
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            painter = painterResource(R.drawable.login),
                            contentDescription = "about",
                            modifier = Modifier.height(60.dp)

                        )
                        Text(
                            text = "Login",
                            fontSize = 15.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold

                        )
                    }

                }

                //Card2(end)

            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(modifier=Modifier.verticalScroll(rememberScrollState())) {


            //Row (end)
            Spacer(modifier = Modifier.height(30.dp))



            //Row2 (end)
            Spacer(modifier = Modifier.height(30.dp))

        }


    }
}











@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    MotorbikeFuelJimcomAppTheme {
        DashboardScreen(rememberNavController())
    }
}