package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.LoanApplicationViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanApplicationScreen(
    ownerId: String,
    motorbikeId: String,
    loanApplicationViewModel: LoanApplicationViewModel = viewModel(),
    navController: NavHostController
) {
    var showApplicationForm by remember { mutableStateOf(false) }
    var selectedApplicationId by remember { mutableStateOf<String?>(null) }
    var showApplicationDetails by remember { mutableStateOf(false) }

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
            FloatingActionButton(onClick = {
                showApplicationForm = true
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add New Loan Application")
            }
        }
    ) { paddingValues ->
        if (showApplicationForm) {
            LoanApplicationForm(
                ownerId = ownerId,
                motorbikeId = motorbikeId,
                onApplicationSubmitted = {
                    showApplicationForm = false
                },
                onDismiss = {
                    showApplicationForm = false
                },
                loanApplicationViewModel = loanApplicationViewModel
            )
        } else if (showApplicationDetails && selectedApplicationId != null) {
            LoanApplicationDetailsScreen(
                applicationId = selectedApplicationId!!,
                onNavigateBack = {
                    showApplicationDetails = false
                    selectedApplicationId = null
                },
                loanApplicationViewModel = loanApplicationViewModel
            )
        } else {
            LoanApplicationScreenContent(
                loanApplicationViewModel = loanApplicationViewModel,
                paddingValues = paddingValues,
                onNavigateToLoanDetails = { applicationId ->
                    selectedApplicationId = applicationId
                    showApplicationDetails = true
                },

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun loan() {
    LoanApplicationScreen(
        ownerId = "1",
        motorbikeId = "1",
        navController = rememberNavController()
    )

}