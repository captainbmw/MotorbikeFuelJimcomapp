package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    onNavigateBack: () -> Unit,
    onNavigateToLoanDetails: (String) -> Unit,
    loanApplicationViewModel: LoanApplicationViewModel = viewModel(),
    navHostController: NavHostController
) {
    val loanApplications = loanApplicationViewModel.loanApplications.observeAsState(emptyList())
    val operationStatus = loanApplicationViewModel.operationStatus.observeAsState()

    var showApplicationForm by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        loanApplicationViewModel.getLoanApplications()
    }
    LoanApplicationScreenContent(
        loanApplications = loanApplications.value,
        operationStatus = operationStatus.value,
        showApplicationForm = showApplicationForm,
        onShowApplicationFormChanged = { showApplicationForm = it },
        ownerId = ownerId,
        motorbikeId = motorbikeId,
        onNavigateBack = onNavigateBack,
        onNavigateToLoanDetails = onNavigateToLoanDetails,
        loanApplicationViewModel = loanApplicationViewModel
    )
    Column(
        modifier = Modifier
            .background(Color.Magenta)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { showApplicationForm = true }) {
            Text("Apply for a loan")


        }
        Button(onClick = { onNavigateBack() }) {
            Text("Back")
        }
        Button(onClick = { onNavigateBack() }) {

        }
    }

}


@Preview(showBackground = true)
@Composable
fun LoanApplicationScreenPreview() {
    LoanApplicationScreen(
        ownerId = "someOwnerId",
        motorbikeId = "someMotorbikeId",
        onNavigateBack = {},
        onNavigateToLoanDetails = {},
        navHostController = rememberNavController()
    )
}