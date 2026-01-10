package com.ahmedezz.madar.domain.usecases.user

import com.ahmedezz.madar.data.local.User
import com.ahmedezz.madar.domain.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsers @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> = repository.getAllUsers()
}