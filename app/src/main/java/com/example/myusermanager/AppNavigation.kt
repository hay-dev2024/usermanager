package com.example.myusermanager

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myusermanager.view.screens.UserListScreen

@Composable
fun AppNavigation(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "UserListScreen") {
        composable("UserListScreen") { UserListScreen(navController) }
    }

}