package com.example.myusermanager.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myusermanager.view.UserViewModel

@Composable
fun UserDetailScreen(navController: NavController, userId: Int, viewModel: UserViewModel) {
//    val viewModel: UserViewModel = viewModel()
    val userList by viewModel.userList.collectAsState()
    val user = userList.find { it.id == userId }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (user != null) {
            Text(text = "ID: ${user.id}")
            Text(text = "이름: ${user.name}")
            Text(text = "이메일: ${user.email}")
            Text(text = "연락처: ${user.phone}")
            Text(text = "주소: ${user.address}")
            Text(text = "등록일: ${user.createdAt}")
        } else {
            Text(text = "사용자 정보를 찾을 수 없습니다.")
        }

        Row {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "뒤로가기")
            }

            Button(
                onClick = {
                    if (user != null) {
                        navController.navigate("UserEditScreen/${user.id}")
                    }
                },
                modifier = Modifier.padding(top = 16.dp, start = 8.dp)
            ) {
                Text(text = "수정")
            }

            Button(
                onClick = {
                    if (user != null) {
                        viewModel.deleteUser(user)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.padding(top = 16.dp, start = 8.dp)
            ) {
                Text(text = "삭제")
            }

        }



    }

}