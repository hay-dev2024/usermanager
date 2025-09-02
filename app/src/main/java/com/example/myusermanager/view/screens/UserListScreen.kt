package com.example.myusermanager.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myusermanager.view.UserViewModel
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(navController: NavController, viewModel: UserViewModel = viewModel()) {
    val userList by viewModel.userList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "사용자 정보관리") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("UserEditScreen") }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add User"
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding( innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (userList.isEmpty()) {
                item {
                    Text(
                        text = "사용자를 추가하세요.",
                        fontSize = 20.sp
                    )
                }
            } else {
                items(userList) { user ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "ID: ${user.id}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Name: ${user.name}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }



        }

    }



}

