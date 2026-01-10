package com.ahmedezz.madar.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmedezz.madar.data.local.User
import com.ahmedezz.madar.ui.composables.InfoRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayScreenContent(
    users: List<User>,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Users") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(users) { user ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 5.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        InfoRow(
                            icon = Icons.Default.Person,
                            iconTint = MaterialTheme.colorScheme.primary,
                            text = user.name,
                            textStyle = MaterialTheme.typography.titleMedium
                        )

                        InfoRow(
                            icon = Icons.Default.Cake,
                            iconTint = MaterialTheme.colorScheme.tertiary,
                            text = "Age: ${user.age}"
                        )

                        InfoRow(
                            icon = Icons.Default.Work,
                            iconTint = MaterialTheme.colorScheme.secondary,
                            text = user.jobTitle
                        )

                        InfoRow(
                            icon = Icons.Default.Face,
                            iconTint = MaterialTheme.colorScheme.primaryContainer,
                            text = user.gender
                        )
                    }
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    device = Devices.PIXEL_7,
    name = "Display Screen - With Users"
)
@Composable
fun DisplayScreenPreview() {
    MaterialTheme {
        DisplayScreenContent(
            users = listOf(
                User(name = "Ahmed", age = 28, jobTitle = "Android Dev", gender = "Male"),
                User(name = "Sara", age = 25, jobTitle = "Designer", gender = "Female")
            )
        )
    }
}
