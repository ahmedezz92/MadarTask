package com.ahmedezz.madar.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmedezz.madar.ui.mvi.users.UserIntent
import com.ahmedezz.madar.ui.mvi.users.UserUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreenContent(
    state: UserUiState,
    onIntent: (UserIntent) -> Unit,
    onNavigateToDisplay: () -> Unit,
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val genders = listOf("Male", "Female")

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("User Details") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = state.name,
                onValueChange = { onIntent(UserIntent.EnterName(it)) },
                label = { Text("Name") },
                isError = state.nameError != null,
                supportingText = {
                    state.nameError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = state.age,
                onValueChange = { onIntent(UserIntent.EnterAge(it)) },
                label = { Text("Age") },
                isError = state.ageError != null,
                supportingText = { state.ageError?.let { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.jobTitle,
                onValueChange = { onIntent(UserIntent.EnterJob(it)) },
                label = { Text("Job Title") },
                isError = state.jobError != null,
                supportingText = { state.jobError?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()

            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = state.gender,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Gender") },
                    isError = state.genderError != null,
                    supportingText = {
                        state.genderError?.let { Text(it) }
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                    },
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    genders.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                onIntent(UserIntent.SelectGender(it))
                                expanded = false
                            }
                        )
                    }
                }
            }

            Button(
                onClick = { onIntent(UserIntent.SaveUser) },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.isValid,

                ) {
                Text("Save")
            }

            OutlinedButton(
                onClick = onNavigateToDisplay,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Users")
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_7,
    name = "Input Screen"
)
@Composable
fun InputScreenPreview() {
    MaterialTheme {
        InputScreenContent(
            state = UserUiState(
                name = "Ahmed",
                age = "28",
                jobTitle = "Android Developer",
                gender = "Male"
            ),
            onIntent = {},
            onNavigateToDisplay = {}
        )
    }
}
