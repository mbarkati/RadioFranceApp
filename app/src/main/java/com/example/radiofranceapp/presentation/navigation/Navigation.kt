package com.example.radiofranceapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.radiofranceapp.common.Constants.BRAND_ID_ARGUMENT
import com.example.radiofranceapp.presentation.brand_list.BrandsScreen
import com.example.radiofranceapp.presentation.show_list.ShowsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Brands.route
    ) {
        composable(
            route = Screen.Brands.route
        ) {
            BrandsScreen(navController)
        }
        composable(
            route = "${Screen.Shows.route}/{${BRAND_ID_ARGUMENT}}",
            arguments = listOf(navArgument(BRAND_ID_ARGUMENT) {
                type = NavType.StringType
            })
        ) {
            ShowsScreen()
        }
    }
}