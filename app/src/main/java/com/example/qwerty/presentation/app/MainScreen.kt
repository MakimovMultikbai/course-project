package com.example.qwerty.presentation.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center)
        {
        Text(text = "Начальный экран приложения", fontSize = 80.sp)
    }
}