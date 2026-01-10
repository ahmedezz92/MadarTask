package com.ahmedezz.madar.domain.repo

import com.ahmedezz.madar.data.local.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)
    fun getAllUsers(): Flow<List<User>>
}