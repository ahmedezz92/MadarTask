package com.example.madar.domain.usecases.user

import com.example.madar.data.local.User
import com.example.madar.domain.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsers @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> = repository.getAllUsers()
}