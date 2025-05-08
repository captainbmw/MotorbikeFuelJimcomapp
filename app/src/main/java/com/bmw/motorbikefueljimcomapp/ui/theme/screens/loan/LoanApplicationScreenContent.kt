package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan





import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bmw.motorbikefueljimcomapp.data.LoanApplicationViewModel
import com.bmw.motorbikefueljimcomapp.model.OperationStatus

@Composable
fun LoanApplicationScreenContent(
    loanApplicationViewModel: LoanApplicationViewModel,
    paddingValues: PaddingValues,
    onNavigateToLoanDetails: (String) -> Unit
) {
    val loanApplications by loanApplicationViewModel.loanApplications.observeAsState(emptyList())
    val operationStatus by loanApplicationViewModel.operationStatus.observeAsState(OperationStatus.Idle)

    LaunchedEffect(Unit) {
        loanApplicationViewModel.getLoanApplications()
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        when (operationStatus) {
            is OperationStatus.Loading -> CircularProgressIndicator()
            is OperationStatus.Error -> Text(
                text = (operationStatus as OperationStatus.Error).message,
                color = MaterialTheme.colorScheme.error
            )

            is OperationStatus.Success -> {
                if (loanApplications.isEmpty()) {
                    Text("No loan applications found.")
                } else {
                    LazyColumn {
                        items(loanApplications) { application ->
                            LoanApplicationItem(
                                application = application,
                                onNavigateToLoanDetails = onNavigateToLoanDetails
                            )
                        }
                    }
                }
            }

            else -> {}
        }
    }
}
