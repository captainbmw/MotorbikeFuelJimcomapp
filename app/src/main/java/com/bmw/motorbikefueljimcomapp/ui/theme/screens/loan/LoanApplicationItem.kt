package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan




import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.model.LoanApplication

@Composable
fun LoanApplicationItem(
    application: LoanApplication,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {}
    ) {
        Column {
            Text("Application ID: ${application.id}", fontWeight = FontWeight.Bold)
            Text("Owner ID: ${application.ownerId}")
            Text("Loan Amount: ${application.loanAmount}")
            Text(
                "Status: ${application.status}",
                fontWeight = FontWeight.Bold,
                color = when (application.status) {
                    "Pending" -> MaterialTheme.colorScheme.primary
                    "Approved" -> MaterialTheme.colorScheme.secondary
                    "Rejected" -> MaterialTheme.colorScheme.error
                    else -> MaterialTheme.colorScheme.onSurface
                }
            )
        }
    }
}

@Preview
@Composable
private fun LoanApplicationItemPreview() {
    LoanApplicationItem(application = LoanApplication(id = "", ownerId = "", motorbikeId = "", loanAmount = 2.0, interestRate = 9.0, applicationDate = 2000),
        rememberNavController()
    )

}