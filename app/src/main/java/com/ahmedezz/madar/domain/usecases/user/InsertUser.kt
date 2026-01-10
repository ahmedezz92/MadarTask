package com.ahmedezz.madar.domain.usecases.user

import com.ahmedezz.madar.data.local.User
import com.ahmedezz.madar.domain.repo.UserRepository
import javax.inject.Inject

class InsertUser @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) = repository.insertUser(user)
}