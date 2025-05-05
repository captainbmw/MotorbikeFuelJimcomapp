package com.bmw.motorbikefueljimcomapp.ui.theme.screens.splash

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.R
import com.bmw.motorbikefueljimcomapp.navigation.ROUTE_HOME
import kotlinx.coroutines.delay

@Composable
fun Splash_page(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(ROUTE_HOME)
    }
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()


    ){
        Image(painter = painterResource(id = R.drawable.bike1),
            contentDescription = "Splash Screen logo",
            modifier = Modifier.size(150.dp)

        )
    }

}

@Preview
@Composable
private fun screen() {
    Splash_page(rememberNavController())
}