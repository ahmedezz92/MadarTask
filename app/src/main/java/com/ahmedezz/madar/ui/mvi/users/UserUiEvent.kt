package com.ahmedezz.madar.ui.mvi.users

sealed class UserUiEvent {
    object UserSavedSuccessfully : UserUiEvent()
}
