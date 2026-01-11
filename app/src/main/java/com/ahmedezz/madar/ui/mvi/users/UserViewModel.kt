package com.ahmedezz.madar.ui.mvi.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedezz.madar.domain.usecases.user.UserUseCases
import com.ahmedezz.madar.data.local.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCases: UserUseCases,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState

    init {
        onIntent(UserIntent.LoadUsers)
    }

    fun onIntent(intent: UserIntent) {
        when (intent) {
            is UserIntent.EnterName ->
                _uiState.update { it.copy(name = intent.name) }

            is UserIntent.EnterAge ->
                _uiState.update { it.copy(age = intent.age) }

            is UserIntent.EnterJob ->
                _uiState.update { it.copy(jobTitle = intent.jobTitle) }

            is UserIntent.SelectGender ->
                _uiState.update { it.copy(gender = intent.gender) }

            is UserIntent.SaveUser -> validateAndSave()
            is UserIntent.LoadUsers -> loadUsers()
            UserIntent.ResetSuccessFlag -> _uiState.update { it.copy(showSuccess = false) }
        }
    }

    private fun validateAndSave() {
        val currentState = _uiState.value
        val validatedState = validate(currentState)

        _uiState.value = validatedState

        if (validatedState.isValid()) {
            saveUser()
        }
    }

    private fun validate(state: UserUiState): UserUiState {
        val ageInt = state.age.toIntOrNull()

        return state.copy(
            nameError = if (state.name.isBlank()) "Name is required" else null,
            ageError = when {
                state.age.isBlank() -> "Age is required"
                ageInt == null -> "Age must be a number"
                ageInt <= 0 -> "Invalid age"
                else -> null
            },
            jobError = if (state.jobTitle.isBlank()) "Job title is required" else null,
            genderError = if (state.gender.isBlank()) "Select gender" else null
        )
    }

    private fun saveUser() {
        val state = validate(_uiState.value)
        _uiState.value = state

        if (!state.isValid) return

        viewModelScope.launch {
            val user = User(
                name = state.name,
                age = state.age.toInt(),
                jobTitle = state.jobTitle,
                gender = state.gender
            )
            useCases.insertUser(user)
            _uiState.value = state.copy(
                showSuccess = true,
                name = "",
                age = "",
                jobTitle = "",
                gender = ""
            )
            onIntent(UserIntent.LoadUsers)
        }
    }


    private fun loadUsers() {
        viewModelScope.launch {
            useCases.getAllUsers().collectLatest { users ->
                _uiState.update { it.copy(userList = users) }
            }
        }
    }

    private fun UserUiState.isValid(): Boolean {
        return nameError == null &&
                ageError == null &&
                jobError == null &&
                genderError == null
    }

}