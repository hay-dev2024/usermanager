package com.example.myusermanager

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myusermanager.view.screens.UserListScreen
import com.example.myusermanager.view.screens.UserEditScreen
import com.example.myusermanager.view.screens.UserDetialScreen

@Composable
fun AppNavigation(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "UserListScreen") {


        composable("UserListScreen") { UserListScreen(navController) }
        composable("UserEditScreen") { UserEditScreen(navController) }
        composable("UserDetailScreen") { UserDetialScreen(navController) }


    }

}