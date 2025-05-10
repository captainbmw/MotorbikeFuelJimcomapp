package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan


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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.LoanApplicationViewModel
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOAN
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOANDETAIL
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOANFORM
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOANITEM
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_MOTORBIKE
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_OWNER
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_UPDATE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanApplicationScreen(
    navController: NavHostController
) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Loan Applications") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate(ROUTE_LOANITEM)           }) {
                Icon(Icons.Filled.Add, contentDescription = "Add New Loan Application")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(14.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                SummaryCard(
                    title = "LoanApplication Detail",
                    onClick = { navController.navigate(ROUTE_LOANDETAIL) },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                SummaryCard(
                    title = "Update Application",
                    onClick = { navController.navigate(ROUTE_UPDATE) },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                SummaryCard(
                    title = "LoanApplication Form",
                    onClick = { navController.navigate(ROUTE_LOANFORM) },
                    modifier = Modifier.weight(1f))
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
private fun loan() {
    LoanApplicationScreen(
        rememberNavController()
    )

}