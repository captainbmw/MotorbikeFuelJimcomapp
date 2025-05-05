package com.bmw.motorbikefueljimcomapp.ui.theme.screens.motorbike

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bmw.motorbikefueljimcomapp.data.MotorbikeRegistrationViewModel
import com.bmw.motorbikefueljimcomapp.data.entities.MotorbikeEntity
import com.bmw.motorbikefueljimcomapp.model.OperationStatus


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotorbikeRegistrationScreen(
    ownerId: String, // Pass the owner ID to link the motorbike
    onNavigateBack: () -> Unit,
    navHostController: NavHostController,
    motorbikeRegistrationViewModel: MotorbikeRegistrationViewModel = viewModel()
) {
    var regNumber by remember { mutableStateOf(TextFieldValue("")) }
    var model by remember { mutableStateOf(TextFieldValue("")) }
    var type by remember { mutableStateOf(TextFieldValue("")) }
    var fuelType by remember { mutableStateOf(TextFieldValue("")) }
    var workStation by remember { mutableStateOf(TextFieldValue("")) }
    var insuranceCompany by remember { mutableStateOf(TextFieldValue("")) }
    var insuranceType by remember { mutableStateOf(TextFieldValue("")) }
    var insuranceExpiry by remember { mutableStateOf(TextFieldValue("")) } // Consider using a Date picker

    val registrationStatus = motorbikeRegistrationViewModel.registrationStatus.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Motorbike Registration") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(value = regNumber, onValueChange = { regNumber = it }, label = { Text("Registration Number") })
            OutlinedTextField(value = model, onValueChange = { model = it }, label = { Text("Model") })
            OutlinedTextField(value = type, onValueChange = { type = it }, label = { Text("Type") })
            OutlinedTextField(value = fuelType, onValueChange = { fuelType = it }, label = { Text("Fuel Type") })
            OutlinedTextField(value = workStation, onValueChange = { workStation = it }, label = { Text("Work Station") })
            OutlinedTextField(value = insuranceCompany, onValueChange = { insuranceCompany = it }, label = { Text("Insurance Company") })
            OutlinedTextField(value = insuranceType, onValueChange = { insuranceType = it }, label = { Text("Insurance Type") })
            OutlinedTextField(value = insuranceExpiry, onValueChange = { insuranceExpiry = it }, label = { Text("Insurance Expiry (YYYY-MM-DD)") })

            Button(onClick = {
                val motorbike = MotorbikeEntity(
                    ownerId = ownerId,
                    regNumber = regNumber.toString(),
                    model = model.toString(),
                    type = type.toString(),
                    fuelType = fuelType.toString(),
                    workStation = workStation.toString(),
                    insuranceCompany = insuranceCompany.toString(),
                    insuranceType = insuranceType.toString(),
                    insuranceExpiry = insuranceExpiry.toString()// Basic conversion
                )
                motorbikeRegistrationViewModel.registerMotorbike(motorbike) { newId ->
                    // Optionally navigate or show a success message with the new ID

                }
            }) {
                Text("Register Motorbike")
            }

            registrationStatus.value?.let { status ->
                when (status) {
                    is OperationStatus.Success -> Text(status.message, color = MaterialTheme.colorScheme.primary)
                    is OperationStatus.Error -> Text(status.message, color = MaterialTheme.colorScheme.error)
                    OperationStatus.Loading -> CircularProgressIndicator()
                    null -> {}
                }
            }
        }
    }
}
