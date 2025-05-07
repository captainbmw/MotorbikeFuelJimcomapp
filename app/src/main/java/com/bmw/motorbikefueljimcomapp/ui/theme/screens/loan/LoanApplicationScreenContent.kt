package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bmw.motorbikefueljimcomapp.data.LoanApplicationViewModel
import com.bmw.motorbikefueljimcomapp.model.LoanApplication
import com.bmw.motorbikefueljimcomapp.model.OperationStatus


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanApplicationScreenContent(
    loanApplications: List<LoanApplication>,
    operationStatus: OperationStatus?,
    showApplicationForm: Boolean,
    onShowApplicationFormChanged: (Boolean) -> Unit,
    ownerId: String,
    motorbikeId: String,
    onNavigateBack: () -> Unit,
    onNavigateToLoanDetails: (String) -> Unit,
    loanApplicationViewModel: LoanApplicationViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Loan Applications") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onShowApplicationFormChanged(true) }) {
                Text("Apply for Loan")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (loanApplications.isEmpty()) {
                Text("No loan applications found.")
            } else {
                LazyColumn {
                    items(loanApplications) { application ->
                        LoanApplicationItemScreen (
                            application = application,
                            onViewDetails = { onNavigateToLoanDetails(application.id) }
                        )
                    }
                }
            }

            operationStatus?.let { status ->
                Spacer(modifier = Modifier.height(16.dp))
                when (status) {
                    is OperationStatus.Success -> Text(status.message, color = MaterialTheme.colorScheme.primary)
                    is OperationStatus.Error -> Text(status.message, color = MaterialTheme.colorScheme.error)
                    OperationStatus.Loading -> CircularProgressIndicator()
                    else -> {}
                }
            }

            if (showApplicationForm) {
                LoanApplicationForm(
                    ownerId = ownerId,
                    motorbikeId = motorbikeId,
                    onApplicationSubmitted = {
                        onShowApplicationFormChanged(false)
                        loanApplicationViewModel.getLoanApplications() // Refresh list

                    },
                    onDismiss = { onShowApplicationFormChanged(false) },
                    loanApplicationViewModel = loanApplicationViewModel
                )
            }
        }
    }
}
