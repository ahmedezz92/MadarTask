package com.example.madar.ui.views.mvi

import com.example.madar.data.local.User

data class UserUiState(
    val name: String = "",
    val age: String = "",
    val jobTitle: String = "",
    val gender: String = "",
    val nameError: String? = null,
    val ageError: String? = null,
    val jobError: String? = null,
    val genderError: String? = null,
    val userList: List<User> = emptyList()
){
    val isValid: Boolean
        get() = nameError == null &&
                ageError == null &&
                jobError == null &&
                genderError == null &&
                name.isNotBlank() &&
                age.isNotBlank() &&
                jobTitle.isNotBlank() &&
                gender.isNotBlank()
}