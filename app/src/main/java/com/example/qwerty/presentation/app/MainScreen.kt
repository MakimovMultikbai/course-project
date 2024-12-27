package com.example.qwerty.presentation.app


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()){
    val state = viewModel.state.value
    val context = LocalContext.current
    viewModel.getCurrentUser()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = state.user.name, fontSize = 40.sp)
        Text(text = state.user.email, fontSize = 40.sp)
        Text(text = state.user.phoneNumber, fontSize = 40.sp)
        Text(text = state.user.birthDate.toString(), fontSize = 40.sp)
    }
}