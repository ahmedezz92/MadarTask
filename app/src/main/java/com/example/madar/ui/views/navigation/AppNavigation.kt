package com.example.madar.ui.views.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.madar.ui.views.mvi.UserViewModel
import com.example.madar.ui.views.screens.DisplayScreenContent
import com.example.madar.ui.views.screens.InputScreenContent

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: UserViewModel,
) {
    NavHost(
        navController = navController, startDestination = "input"
    ) {

        composable("input") {
            val state by viewModel.uiState.collectAsState()

            InputScreenContent(
                state = state, onIntent = viewModel::onIntent, onNavigateToDisplay = {
                    navController.navigate("display")
                })
        }

        composable("display") {
            val state by viewModel.uiState.collectAsState()

            DisplayScreenContent(
                users = state.userList
            )
        }
    }
}
