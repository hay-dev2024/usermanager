package com.example.myusermanager.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myusermanager.data.User
import com.example.myusermanager.data.UserDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val userDao = UserDatabase.getDatabase(application).userDao()

    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList: StateFlow<List<User>> = _userList.asStateFlow()
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()
    private val _userEmail = MutableStateFlow("")
    val userEmail: StateFlow<String> = _userEmail.asStateFlow()
    private val _userPhone = MutableStateFlow("")
    val userPhone: StateFlow<String> = _userPhone.asStateFlow()
    private val _userAddress = MutableStateFlow("")
    val userAddress: StateFlow<String> = _userAddress.asStateFlow()
    private val _userCreatedAt = MutableStateFlow("")
    val userCreatedAt: StateFlow<String> = _userCreatedAt.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _userList.value = userDao.getAllUsers()
        }
    }

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun setUserEmail(email: String) {
        _userEmail.value = email
    }

    fun setUserPhone(phone: String) {
        _userPhone.value = phone
    }

    fun setUserAddress(address: String) {
        _userAddress.value = address
    }

    fun addUser() {
        viewModelScope.launch {
            if (_userName.value.isNotBlank()) {
                val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())
                val user = User(
                    name = userName.value,
                    email = userEmail.value,
                    phone = userPhone.value,
                    address = userAddress.value,
                    createdAt = currentDate
                )
                userDao.insertUser(user)
                loadUsers()
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            val existingUser = userDao.getAllUsers().find { it.id == user.id }
            if (existingUser != null) {
                userDao.deleteUser(existingUser)
                loadUsers()
            }
        }
    }



}