package com.example.myusermanager.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun UserEditScreen(navController: NavController) {

    Column() {
        Text(
            text = "사용자 정보 등록 및 수정"
        )
    }

}