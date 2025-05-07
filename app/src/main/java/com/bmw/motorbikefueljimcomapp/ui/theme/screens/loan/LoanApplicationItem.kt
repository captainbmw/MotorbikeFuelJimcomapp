package com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmw.motorbikefueljimcomapp.R
import com.bmw.motorbikefueljimcomapp.model.LoanApplication
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun LoanApplicationItemScreen(
    application: LoanApplication,
    onViewDetails: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.bike3), contentScale = ContentScale.FillBounds)
            .padding(vertical = 8.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Text("Application Date: ${
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
                Date(application.applicationDate)
            )
        }")
        Text("Loan Amount: ${application.loanAmount}")
        Text("Status: ${application.status}", fontWeight = FontWeight.Bold)
        Button(onClick = onViewDetails) {
            Text("View Details")
        }
    }
}

@Preview
@Composable
private fun LoanApplicationItemPreview(): Unit {

    
}