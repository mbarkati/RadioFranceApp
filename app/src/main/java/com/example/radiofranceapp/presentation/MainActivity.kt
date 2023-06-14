package com.example.radiofranceapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.radiofranceapp.common.Constants
import com.example.radiofranceapp.presentation.brand_list.BrandsScreen
import com.example.radiofranceapp.presentation.show_list.ShowsScreen
import com.example.radiofranceapp.presentation.show_list.ShowsViewModel
import com.example.radiofranceapp.presentation.ui.theme.RadioFranceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RadioFranceAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
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
                            route = Screen.Shows.route + "/{${Constants.BRAND_ID_ARGUMENT}}"
                        ) {
                            val viewModel = hiltViewModel<ShowsViewModel>()
                            ShowsScreen(
                                viewModel = viewModel,
                                onPaginate = viewModel::getShows
                            )
                        }
                    }
                }
            }
        }
    }
}
