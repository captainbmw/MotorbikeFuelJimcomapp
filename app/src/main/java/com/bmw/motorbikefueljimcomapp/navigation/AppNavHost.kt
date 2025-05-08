package com.bmw.motorbikefueljimcomapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.Repayment.RepaymentScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.home.HomeScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan.LoanApplicationScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.login.LoginScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.motorbike.MotorbikeRegistrationScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.owner.OwnerRegistrationScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.register.RegisterScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.splash.Splash_page


@Composable
fun AppNavHost(modifier: Modifier=Modifier,navController:NavHostController= rememberNavController(),startDestination:String= ROUTE_SPLASH) {

    NavHost(navController = navController, modifier=modifier, startDestination = startDestination ){
        composable(ROUTE_SPLASH) {
            Splash_page(navController)

        }
        composable(ROUTE_HOME){
           HomeScreen(navController,ownerCount = 0,motorbikeCount = 0,loanCount = 0)

        }
        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_REGISTER){
            RegisterScreen(navController)
        }


        composable(ROUTE_OWNER){
            OwnerRegistrationScreen(onNavigateBack = {navController.popBackStack()}, navController = 33.dp)

        }
        composable(ROUTE_MOTORBIKE) {
            MotorbikeRegistrationScreen(navController)
        }
        composable(ROUTE_REPAYMENT) {
            RepaymentScreen(loanId = "", onNavigateBack = {navController.popBackStack()})
        }
        composable(ROUTE_LOAN) {
            LoanApplicationScreen(ownerId = "", motorbikeId = "", navController = navController)




        }


    }
}