package com.example.qwerty.presentation.app


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.qwerty.R
import com.example.qwerty.domain.models.Card
import com.example.qwerty.navigation.NavRoutes
import com.example.qwerty.presentation.ui.theme.HyperColor
import com.google.gson.Gson


@OptIn(ExperimentalMaterial3Api::class)
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
        IconButton(
            onClick = {
                viewModel.exit(context)
                navController.navigate(NavRoutes.AuthGraph.route){
                    popUpTo(NavRoutes.AppGraph.route) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.exit),
                modifier = Modifier.size(32.dp),
                tint = Color.Black,
                contentDescription = null
            )
        }
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
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Text(
                            text = state.user.userName.ifEmpty { "Не указано" },
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = state.user.email.ifEmpty { "Email не указан" },
                            fontSize = 16.sp,
                            color = Color.Gray
                        )

                        Text(
                            text = state.user.phoneNumber.ifEmpty { "Телефон не указан" },
                            fontSize = 16.sp
                        )

                        Text(
                            text = state.user.dayOfBirth?.let { "Дата рождения: ${it.day}.${it.month}.${it.year}" }
                                ?: "Дата не указана",
                            fontSize = 16.sp
                        )

                        Spacer(Modifier.height(16.dp))
                    }

                    if (state.user.cards.isNotEmpty()) {
                        itemsIndexed(state.user.cards) { index, model ->

                            CardItem(model){
                                navController.navigate(NavRoutes.CardDetails.route + "/${
                                    Gson().toJson(model)
                                }")
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                        }
                    }

                    item {
                        Button(
                            onClick = {
                                navController.navigate(NavRoutes.AddCard.route)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color(0xFFFFFFFF),
                                containerColor = HyperColor
                            )
                        ) {
                            Text(text = "Добавить карту")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CardItem(
    card: Card,
    onClick: () -> Unit
) {
    Card(
        Modifier.background(Color.Gray, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                onClick()
            }
    ) {
        Column(
            Modifier.padding(8.dp)
        ) {
            Text("Номер: ${card.number.takeIf { it.isNotEmpty() } ?: "Не указан"}")
            Text("Статус: ${card.status.takeIf { it.isNotEmpty() } ?: "Не указан"}")
            Text("Баланс: ${card.balance}")
        }
    }

}



