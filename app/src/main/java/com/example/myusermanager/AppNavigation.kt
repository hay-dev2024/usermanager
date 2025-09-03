package com.example.myusermanager

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myusermanager.view.UserViewModel
import com.example.myusermanager.view.screens.UserListScreen
import com.example.myusermanager.view.screens.UserEditScreen
import com.example.myusermanager.view.screens.UserDetailScreen

@Composable
fun AppNavigation(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    NavHost(navController = navController, startDestination = "UserListScreen") {
        composable("UserListScreen") { UserListScreen(navController, userViewModel) }
        composable("UserEditScreen") { UserEditScreen(navController, null, userViewModel) }
        composable(
            "UserEditScreen/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")
            UserEditScreen(navController, userId, userViewModel)
        }
        composable(
            "UserDetailScreen/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            UserDetailScreen(navController, userId, userViewModel)
        }
    }

}