package com.bmw.motorbikefueljimcomapp.ui.theme.screens.motorbike



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.MotorbikeRegistrationViewModel
import com.bmw.motorbikefueljimcomapp.data.entities.MotorbikeEntity
import com.bmw.motorbikefueljimcomapp.model.OperationStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotorbikeRegistrationScreen(
    navController: NavHostController,
    motorbikeRegistrationViewModel: MotorbikeRegistrationViewModel = viewModel()
) {
    var brand by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }

    val operationStatus by motorbikeRegistrationViewModel.registrationStatus.observeAsState()

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Register Motorbike") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = brand,
                onValueChange = { brand = it },
                label = { Text("Brand") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = model,
                onValueChange = { model = it },
                label = { Text("Model") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = year,
                onValueChange = { year = it },
                label = { Text("Year") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = color,
                onValueChange = { color = it },
                label = { Text("Color") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            when (operationStatus) {
                is OperationStatus.Loading -> {
                    CircularProgressIndicator()
                }

                is OperationStatus.Error -> {
                    Text(
                        text = (operationStatus as OperationStatus.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                is OperationStatus.Success -> {
                    // Reset form fields if successful
                    brand = ""
                    model = ""
                    year = ""
                    color = ""
                    Text(
                        text = (operationStatus as OperationStatus.Success).message,
                        color = MaterialTheme.colorScheme.primary
                    )

                }
                else -> {
                    Button(
                        onClick = {
                            if (brand.isNotEmpty() && model.isNotEmpty() && year.isNotEmpty() && color.isNotEmpty()) {
                                motorbikeRegistrationViewModel.registerMotorbike(
                                    MotorbikeEntity(
                                        brand = brand,
                                        model = model,
                                        year = year.toInt(),
                                        color = color,
                                        ownerId = "",
                                        id = TODO(),
                                        regNumber = TODO(),
                                        type = TODO(),
                                        fuelType = TODO(),
                                        workStation = TODO(),
                                        insuranceCompany = TODO(),
                                        insuranceType = TODO(),
                                        insuranceExpiry = TODO(),
                                        status = TODO(),
                                        createdAt = TODO(),
                                        updatedAt = TODO()
                                    )
                                ) { newMotorbikeId ->
                                    // Handle the new motorbike ID here
                                    // e.g., navigate to a details screen
                                    // or show a confirmation message
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Register Motorbike")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MotorbikeRegistrationScreenPreview() {
    MotorbikeRegistrationScreen(rememberNavController())
}