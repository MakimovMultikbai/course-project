package com.example.qwerty.domain.repository.app


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        contentAlignment = Alignment.Center)
        {
        Text(text = "Начальный экран приложения", fontSize = 40.sp)

    }
    /*Button(onClick = { viewModel.getCurrentUser()}) {
        Text(text = "send")
    }*/
}