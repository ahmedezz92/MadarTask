package com.ahmedezz.madar.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmedezz.madar.ui.mvi.users.UserViewModel
import com.ahmedezz.madar.ui.screens.DisplayScreenContent
import com.ahmedezz.madar.ui.screens.InputScreenContent

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
