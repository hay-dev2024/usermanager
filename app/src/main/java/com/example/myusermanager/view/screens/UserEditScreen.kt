package com.example.myusermanager.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myusermanager.view.UserViewModel

@Composable
fun UserEditScreen(navController: NavController) {
    val viewModel: UserViewModel = viewModel()
    val userName by viewModel.userName.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "사용자 정보 등록 및 수정"
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = { viewModel.userName.value = it },
            placeholder = { Text("사용자 이름을 입력하세요") },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        )
    }

}