package com.example.madar.data.repo

import com.example.madar.domain.repo.UserRepository
import com.example.madar.data.local.User
import com.example.madar.data.local.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao
) : UserRepository {
    override suspend fun insertUser(user: User) = dao.insertUser(user)
    override fun getAllUsers(): Flow<List<User>> = dao.getAllUsers()
}