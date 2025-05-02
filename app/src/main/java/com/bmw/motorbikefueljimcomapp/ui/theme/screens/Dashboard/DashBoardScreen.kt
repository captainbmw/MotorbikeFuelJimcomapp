package com.bmw.motorbikefueljimcomapp.ui.theme.screens.Dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bmw.motorbikefueljimcomapp.data.LoanViewModel
import com.bmw.motorbikefueljimcomapp.data.OwnerViewModel
import com.bmw.motorbikefueljimcomapp.data.entities.LoanEntity
import com.bmw.motorbikefueljimcomapp.data.entities.OwnerEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    loanViewModel: LoanViewModel = viewModel(),
    ownerViewModel: OwnerViewModel = viewModel(),
    onNavigateToLoanApplication: () -> Unit,
    onNavigateToMotorbikeRegistration: () -> Unit,
    onNavigateToDevotion: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("JIMCOM Dashboard") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        DashboardContent(
            modifier = Modifier.padding(innerPadding),
            loanViewModel = loanViewModel,
            ownerViewModel = ownerViewModel,
            onNavigateToLoanApplication = onNavigateToLoanApplication,
            onNavigateToMotorbikeRegistration = onNavigateToMotorbikeRegistration,
            onNavigateToDevotion = onNavigateToDevotion
        )
    }
}

@Composable
fun DashboardContent(
    modifier: Modifier = Modifier,
    loanViewModel: LoanViewModel,
    ownerViewModel: OwnerViewModel,
    onNavigateToLoanApplication: () -> Unit,
    onNavigateToMotorbikeRegistration: () -> Unit,
    onNavigateToDevotion: () -> Unit
) {
    val allLoans: List<LoanEntity> by loanViewModel.allLoans.observeAsState(initial = emptyList())
    val allOwners: List<OwnerEntity> by ownerViewModel.allOwners.observeAsState(initial = emptyList())

    val activeLoansCount = allLoans.count { it.status == "Active" }
    val overdueLoansCount = allLoans.count { loan ->
        loan.status == "Active" &&
                loan.dueDate.parseDate("")?.before(java.util.Date()) == true // Assuming you have a parseDate() extension
    }
    val totalLoanAmount = allLoans.filter { it.status == "Active" }.sumOf { it.totalPayable }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Welcome to JIMCOM!",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DashboardCard(title = "Active Loans", value = activeLoansCount.toString())
            DashboardCard(title = "Overdue Loans", value = overdueLoansCount.toString())
        }

        DashboardCard(
            title = "Total Active Loan Amount",
            value = "KES ${String.format("%.2f", totalLoanAmount)}"
        )
        DashboardCard(title = "Registered Owners", value = allOwners.size.toString())

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = onNavigateToLoanApplication
            ) {
                Text("Apply for Loan")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = onNavigateToMotorbikeRegistration
            ) {
                Text("Register Motorbike")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onNavigateToDevotion
        ) {
            Text("Today's Devotion")
        }
    }
}

@Composable
fun DashboardCard(title: String, value: String) {
    Card(
        modifier = Modifier.size(width = 180.dp, height = 120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                value,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

// Extension function to parse date string (you might have a utility for this)
fun String.parseDate(): java.util.Date? {
    return try {
        java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).parse(this)
    } catch (e: Exception) {
        null
    }
}