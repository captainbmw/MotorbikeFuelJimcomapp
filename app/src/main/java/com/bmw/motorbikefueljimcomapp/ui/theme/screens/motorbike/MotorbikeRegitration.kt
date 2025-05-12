package com.bmw.motorbikefueljimcomapp.ui.theme.screens.motorbike


import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.MotorbikeViewModel
import com.bmw.motorbikefueljimcomapp.model.Motorbike
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_MOTORBIKE
import com.bmw.motorbikefueljimcomapp.ui.theme.MotorbikeFuelJimcomAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotorbikeScreen(navController: NavHostController) {
    val context = LocalContext.current


    // Observe the list of motorbikes from the ViewModel
    var numberplate by remember { mutableStateOf(TextFieldValue("")) }
    var brand by remember { mutableStateOf(TextFieldValue("")) }
    var model by remember { mutableStateOf(TextFieldValue("")) }
    var color by remember { mutableStateOf(TextFieldValue("")) }



    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Motorbikes") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(ROUTE_MOTORBIKE) }) {
                Icon(Icons.Filled.Add, "Add New Motorbike")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = numberplate,
                onValueChange = { numberplate = it },
                label = { Text("Number Plate") },
                modifier = Modifier.fillMaxWidth()

            )
            OutlinedTextField(
                value = brand,
                onValueChange = { brand = it },
                label = { Text("Brand") },
                modifier = Modifier.fillMaxWidth())
            OutlinedTextField(
                value = model,
                onValueChange = { model = it },
                label = { Text("Model") },
                modifier = Modifier.fillMaxWidth())
            OutlinedTextField(
                value = color,
                onValueChange = { color = it },
                label = { Text("Color") },
                modifier = Modifier.fillMaxWidth())

            Button(onClick = {
                var myMotorbike= MotorbikeViewModel(navController,context)
                myMotorbike.saveMotorbike(numberplate.text.trim(),brand.text.trim(),model.text.trim(),color.text.trim())
            }) {
                Text(text = "Save Motorbike")

            }


        }
    }
}

@Composable
fun MotorbikeItem(motorbike: Motorbike) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Number Plate: ${motorbike.numberPlate}")
        Text("Brand: ${motorbike.brand}")
        Text("Model: ${motorbike.model}")
        Text("Color: ${motorbike.color}")
    }
}


@Preview(showBackground = true)
@Composable
fun MotorbikeScreenPreview() {
    MotorbikeFuelJimcomAppTheme {
        MotorbikeScreen(rememberNavController())
    }
}