package com.example.madar.domain.repo

import com.example.madar.data.local.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)
    fun getAllUsers(): Flow<List<User>>
}