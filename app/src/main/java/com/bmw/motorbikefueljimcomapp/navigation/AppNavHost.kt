package com.bmw.motorbikefueljimcomapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.About.AboutScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.Dashboard.DashboardScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.Insurance.InsuranceRegistrationScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.Repayment.RepaymentScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.home.HomeScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan.ApplyLoanScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan.UpdateLoanApplication
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.loan.ViewUploadLoans
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.login.LoginScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.motorbike.MotorbikeScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.owner.OwnerRegistrationScreen
import com.bmw.motorbikefueljimcomapp.ui.theme.screens.splash.Splash_page


@Composable
fun AppNavHost(modifier: Modifier=Modifier,navController:NavHostController= rememberNavController(),startDestination:String= ROUTE_SPLASH) {

    NavHost(navController = navController, modifier=modifier, startDestination = startDestination ){
        composable(ROUTE_SPLASH) {
            Splash_page(navController)

        }
        composable(ROUTE_HOME){
           HomeScreen(navController)

        }
        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }



        composable(ROUTE_OWNER){
            OwnerRegistrationScreen(navController)

        }
        composable(ROUTE_MOTORBIKE) {
            MotorbikeScreen(navController)
        }
        composable(ROUTE_REPAYMENT) {
            RepaymentScreen(
                navController,

            )
        }
        composable(ROUTE_LOAN) {
            ApplyLoanScreen(navController)
        }
        composable(ROUTE_VIEW_LOAN) {
            ViewUploadLoans(navController)

        }
        composable(ROUTE_UPDATE) {
            UpdateLoanApplication(navController, id = "")

        }

        composable(ROUTE_INSURANCE) {
            InsuranceRegistrationScreen(navController)


        }
        composable(ROUTE_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUTE_DASHBOARD) {
            DashboardScreen(navController)
        }


    }
}