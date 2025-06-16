package com.example.qwerty.presentation.app


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.qwerty.domain.models.UserData


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getCurrentUser()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Ошибка: ${state.error}", color = Color.Red)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.getCurrentUser() }) {
                        Text("Повторить")
                    }
                }
            }

            else -> {
                UserInfoSection(state.user)
            }
        }
    }
}

@Composable
private fun UserInfoSection(user: UserData) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = user.name.ifEmpty { "Не указано" },
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = user.email.ifEmpty { "Email не указан" },
            fontSize = 16.sp,
            color = Color.Gray
        )

        Text(
            text = user.phoneNumber.ifEmpty { "Телефон не указан" },
            fontSize = 16.sp
        )

        Text(
            text = user.dayOfBirth?.let { "Дата рождения: ${it.day}.${it.month}.${it.year}" }
                ?: "Дата не указана",
            fontSize = 16.sp
        )

        if (user.cards.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Карты:", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    user.cards.forEach { card ->
                        CardItem(card)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun CardItem(card: UserData.Card) {
    Column {
        Text("Номер: ${card.number.takeIf { it.isNotEmpty() } ?: "Не указан"}")
        Text("Статус: ${card.status.takeIf { it.isNotEmpty() } ?: "Не указан"}")
        Text("Баланс: ${card.balance}")
    }
}



