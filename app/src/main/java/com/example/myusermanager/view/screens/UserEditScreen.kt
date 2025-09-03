package com.example.myusermanager.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myusermanager.view.UserViewModel

@Composable
fun UserEditScreen(navController: NavController, userId: Int? = null, viewModel: UserViewModel) {
//    val viewModel: UserViewModel = viewModel()
    val userList by viewModel.userList.collectAsState()
    val user = userList.find { it.id == userId }

    LaunchedEffect(userId) {
        if (userId == null) {
            viewModel.setUserName("")
            viewModel.setUserEmail("")
            viewModel.setUserPhone("")
            viewModel.setUserAddress("")
        } else {
            user?.let {
                viewModel.setUserName(it.name)
                viewModel.setUserEmail(it.email)
                viewModel.setUserPhone(it.phone)
                viewModel.setUserAddress(it.address)
            }
        }
    }

    val userName by viewModel.userName.collectAsState()
    val userEmail by viewModel.userEmail.collectAsState()
    val userPhone by viewModel.userPhone.collectAsState()
    val userAddress by viewModel.userAddress.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = if (user != null) "사용자 정보 수정" else "사용자 정보 등록")

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(text = "이름: ", modifier = Modifier.padding(top = 12.dp))

            OutlinedTextField(
                value = userName,
                onValueChange = { viewModel.setUserName(it) },
                placeholder = { Text("사용자 이름을 입력하세요") },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(text = "이메일: ", modifier = Modifier.padding(top = 12.dp))

            OutlinedTextField(
                value = userEmail,
                onValueChange = { viewModel.setUserEmail(it) },
                placeholder = { Text("사용자 이메일을 입력하세요") },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(text = "연락처: ", modifier = Modifier.padding(top = 12.dp))

            OutlinedTextField(
                value = userPhone,
                onValueChange = { viewModel.setUserPhone(it) },
                placeholder = { Text("사용자 연락처를 입력하세요") },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(text = "주소: ", modifier = Modifier.padding(top = 12.dp))

            OutlinedTextField(
                value = userAddress,
                onValueChange = { viewModel.setUserAddress(it) },
                placeholder = { Text("사용자 주소를 입력하세요") },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Button(
                onClick = {
                    if (user != null) {
                        // 수정
                        val updatedUser = user.copy(
                            name = userName,
                            email = userEmail,
                            phone = userPhone,
                            address = userAddress
                        )
                        viewModel.updateUser(updatedUser)
                    } else {
                        viewModel.addUser()
                    }
                    navController.popBackStack()
                }
            ) {
                Text(text = "저장")
            }

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(text = "취소")
            }
        }
    }
}