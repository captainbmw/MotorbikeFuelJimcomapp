package com.bmw.motorbikefueljimcomapp.ui.theme.screens.Insurance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.InsuranceRegistrationViewModel
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOGIN
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_OWNER
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_REPAYMENT
import com.bmw.motorbikefueljimcomapp.ui.theme.MotorbikeFuelJimcomAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsuranceRegistrationScreen(navController: NavHostController) {
    val context = LocalContext.current


    var insuranceCompany by remember { mutableStateOf(TextFieldValue("")) }
    var policyNumber by remember { mutableStateOf(TextFieldValue("")) }
    var startDate by remember { mutableStateOf(TextFieldValue("")) }
    var expiryDate by remember { mutableStateOf(TextFieldValue("")) }
    var coverageType by remember { mutableStateOf(TextFieldValue("")) }
    var premiumAmount by remember { mutableStateOf(TextFieldValue("")) }

   Scaffold(
       topBar = {
           TopAppBar(title = {
               Text(
                   "Motorbike Fuel Jimcom App",
                   fontWeight = FontWeight.Bold,
                   style = MaterialTheme.typography.titleLarge,
                   color = Color.Blue,
                   modifier = Modifier
                       .padding(start = 16.dp),
               )
           })
       },

       floatingActionButton = {
           FloatingActionButton(onClick = { navController.navigate(ROUTE_OWNER) }) {
               Icon(Icons.Filled.Add, contentDescription = "Add New Owner")
           }
       },
       bottomBar = {
           NavigationBar(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp)
           ) {
               NavigationBarItem(onClick = { navController.navigate(ROUTE_HOME)},
                   icon = { Icon(Icons.Filled.Home, contentDescription = "Add New Owner") },
                   label = { Text("Home") },
                   selected = true)
               NavigationBarItem(onClick = { navController.navigate(ROUTE_LOGIN)},
                   icon = { Icon(Icons.Filled.Person, contentDescription = "Login") },
                   label = { Text("Login") },
                   selected = true)
               NavigationBarItem(onClick = { navController.navigate(ROUTE_REPAYMENT)},
                   icon = { Icon(Icons.Filled.MoreVert, contentDescription = "Add New Owner") },
                   label = { Text("Repayment") },
                   selected = true)
               NavigationBarItem(onClick = { navController.navigate(ROUTE_OWNER)},
                   icon = { Icon(Icons.Filled.Person, contentDescription = "Owner") },
                   label = { Text("Owner") },
                   selected = true)

           }
       }

   ) {paddingValues ->
       Column(
           modifier = Modifier
               .padding(paddingValues)
               .fillMaxSize()
               .padding(16.dp),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ) {
           Text("Insurance Registration",
               style = MaterialTheme.typography.headlineMedium,
               color = Color.Black,
               fontWeight = FontWeight.Bold)

           OutlinedTextField(
               value = insuranceCompany,
               shape = RoundedCornerShape(16.dp),
               onValueChange = { insuranceCompany = it },
               label = { Text("Insurance Company") },
               modifier = Modifier.fillMaxWidth()
           )

           OutlinedTextField(
               value = policyNumber,
               shape = RoundedCornerShape(16.dp),
               onValueChange = { policyNumber = it },
               label = { Text("Policy Number") },
               modifier = Modifier.fillMaxWidth()
           )

           OutlinedTextField(
               value = startDate,
               shape = RoundedCornerShape(16.dp),
               onValueChange = { startDate = it },
               label = { Text("Start Date") },
               modifier = Modifier.fillMaxWidth()
           )

           OutlinedTextField(
               shape = RoundedCornerShape(16.dp),
               value = expiryDate,
               onValueChange = { expiryDate = it },
               label = { Text("Expiry Date") },
               modifier = Modifier.fillMaxWidth()
           )

           OutlinedTextField(
               value = coverageType,
               shape = RoundedCornerShape(16.dp),
               onValueChange = { coverageType = it },
               label = { Text("Coverage Type") },
               modifier = Modifier.fillMaxWidth()
           )

           OutlinedTextField(
               value = premiumAmount,
               onValueChange = { premiumAmount = it },
               label = { Text("Premium Amount") },
               modifier = Modifier.fillMaxWidth()
           )

           Spacer(modifier = Modifier.height(16.dp))

           Button(
               onClick = {
                   val myinsurance = InsuranceRegistrationViewModel(navController, context)
                   myinsurance.saveInsuranceRegistration(
                       insuranceCompany.text.trim(),
                       policyNumber.text.trim(),
                       startDate.text.trim(),
                       expiryDate.text.trim(),
                       coverageType.text.trim(),
                       premiumAmount.text.trim()
                   )


               },
               modifier = Modifier.fillMaxWidth()
           ) {
               Text("Save Registration")
           }
       }
   }
}



@Preview(showBackground = true)
@Composable
fun InsuranceRegistrationScreenPreview() {
    MotorbikeFuelJimcomAppTheme {
        InsuranceRegistrationScreen(rememberNavController())
    }
}