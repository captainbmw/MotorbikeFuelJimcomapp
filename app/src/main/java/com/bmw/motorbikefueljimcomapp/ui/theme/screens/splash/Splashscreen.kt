package com.bmw.motorbikefueljimcomapp.ui.theme.screens.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.R
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_LOGIN
import kotlinx.coroutines.delay

@Composable
fun Splash_page(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(ROUTE_HOME)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Motorbike Fuel Jimcom App",
            color = Color.White,
            fontSize = 30.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(50.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center)


        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.Black)

                .padding(20.dp
                )


        ){
            Image(painter = painterResource(id = R.drawable.bike0),
                contentDescription = "bike",
                modifier = Modifier.size(500.dp),
                alignment = Alignment.Center

            )
            Spacer(modifier = Modifier.height(10.dp))


        }

    }



}

@Preview
@Composable
private fun screen() {
    Splash_page(rememberNavController())
}