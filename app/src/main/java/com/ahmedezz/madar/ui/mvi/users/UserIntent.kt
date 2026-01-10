package com.ahmedezz.madar.ui.mvi.users

sealed class UserIntent {
    data class EnterName(val name: String): UserIntent()
    data class EnterAge(val age: String): UserIntent()
    data class EnterJob(val jobTitle: String): UserIntent()
    data class SelectGender(val gender: String): UserIntent()
    object SaveUser: UserIntent()
    object LoadUsers: UserIntent()
}