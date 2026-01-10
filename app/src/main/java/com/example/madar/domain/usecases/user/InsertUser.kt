package com.example.madar.domain.usecases.user

import com.example.madar.data.local.User
import com.example.madar.domain.repo.UserRepository
import javax.inject.Inject

class InsertUser @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) = repository.insertUser(user)
}