package com.bmw.motorbikefueljimcomapp.ui.theme.screens.register


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.R
import com.bmw.motorbikefueljimcomapp.data.AuthViewModel
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOGIN
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_OWNER
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_REGISTER
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_REPAYMENT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController:NavHostController) {
    var firstname by remember { mutableStateOf(TextFieldValue("")) }
    var lastname by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var confirmpass by remember { mutableStateOf(TextFieldValue("")) }
    var context= LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "Motorbike Fuel Jimcom App",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Blue,
                    modifier = Modifier.padding(start = 16.dp),
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
                    .padding(8.dp)

            ) {
                NavigationBarItem(onClick = { navController.navigate(ROUTE_LOGIN)},
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Add New Owner") },
                    label = { Text("Home") },
                    selected = false)
                NavigationBarItem(onClick = { navController.navigate(ROUTE_LOGIN)},
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Login") },
                    label = { Text("Login") },
                    selected = false)
                NavigationBarItem(onClick = { navController.navigate(ROUTE_REGISTER)},
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Register") },
                    label = { Text("Register") },
                    selected = false)

            }
        }
    ){paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.bike3), contentScale = ContentScale.FillBounds),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = "Register page",
                color = Color.Cyan,
                fontFamily = FontFamily.Monospace,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold)


            OutlinedTextField(
                value = firstname, onValueChange = { firstname = it },
                label = { Text(text = "Enter Firstname") },
                shape = RoundedCornerShape(16.dp),

                keyboardOptions = KeyboardOptions . Default . copy (imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White),

                )



            OutlinedTextField(
                value = lastname, onValueChange = { lastname = it },
                label = { Text(text = "Enter Lastname") },
                shape = RoundedCornerShape(16.dp),

                keyboardOptions = KeyboardOptions . Default . copy (imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White),

                )


            OutlinedTextField(
                value = email, onValueChange = { email = it },
                label = { Text(text = "Enter Email") },
                shape = RoundedCornerShape(16.dp),

                keyboardOptions = KeyboardOptions . Default . copy (imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White),

                )


            OutlinedTextField(value =pass , onValueChange = {pass=it},
                label = { Text(text = "Enter password") },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White)
            )

            OutlinedTextField(value =confirmpass , onValueChange = {
                confirmpass=it},
                label = { Text(text = "Enter Confirm Pass") },
                shape = RoundedCornerShape(16.dp),

                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White)
            )



            Button(onClick = {
                val myregister= AuthViewModel(navController,context)
                myregister.signup(firstname.text.trim(),lastname.text.trim(),email.text.trim(),pass.text.trim(),confirmpass.text.trim())




            }, modifier = Modifier.width(300.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = "Register ",
                    color = Color.Cyan)
            }
            Spacer(modifier = Modifier.height(6.dp))

            Text(text = "Have an Account? Click to Login",
                modifier = Modifier.clickable { navController.navigate(ROUTE_LOGIN) },
                color = Color.Blue,
                fontSize = 20.sp)

        }



    }
    }

@Preview
@Composable
fun register() {
    RegisterScreen(rememberNavController())

}