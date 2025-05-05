package com.bmw.motorbikefueljimcomapp.ui.theme.screens.home
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bmw.motorbikefueljimcomapp.data.HomeScreenViewModel
import com.bmw.motorbikefueljimcomapp.model.OperationStatus

import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel = viewModel()
) {
    val activeOwnerCount = homeScreenViewModel.activeOwnerCount.observeAsState(0)
    val activeLoanCount = homeScreenViewModel.activeLoanCount.observeAsState(0)
    val operationStatus = homeScreenViewModel.operationStatus.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Dashboard") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Show a menu of add options */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Add New")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Greeting
            val calendar = Calendar.getInstance()
            val timeOfDay = when (calendar.get(Calendar.HOUR_OF_DAY)) {
                in 0..11 -> "Good Morning"
                in 12..16 -> "Good Afternoon"
                else -> "Good Evening"
            }
            Text("$timeOfDay!", style = MaterialTheme.typography.headlineLarge)

            // Summary Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SummaryCard(
                    title = "Active Owners",
                    count = activeOwnerCount.value,
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                SummaryCard(
                    title = "Active Loans",
                    count = activeLoanCount.value,
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                // You can add more summary cards here for Motorbikes, etc.
            }

            // Quick Actions Row
            Text(
                "Quick Actions",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 8.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {}) {
                    Text("Add Owner")
                }
                Button(onClick = { /* TODO: Navigate to select owner for motorbike */ }) {
                    Text("Add Motorbike")
                }
                Button(onClick = { /* TODO: Navigate to select owner & motorbike for loan */ }) {
                    Text("Apply Loan")
                }
            }

            // Operation Status Display
            operationStatus.value?.let { status ->
                Spacer(modifier = Modifier.height(16.dp))
                when (status) {
                    is OperationStatus.Success -> Text(status.message, color = MaterialTheme.colorScheme.primary)
                    is OperationStatus.Error -> Text(status.message, color = MaterialTheme.colorScheme.error)
                    OperationStatus.Loading -> CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun SummaryCard(title: String, count: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),


    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(count.toString(), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        }
    }
}