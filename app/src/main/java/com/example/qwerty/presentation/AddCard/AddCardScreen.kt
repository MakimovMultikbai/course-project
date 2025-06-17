package com.example.qwerty.presentation.AddCard


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.qwerty.presentation.common.CustomTF
import com.example.qwerty.presentation.ui.theme.HyperColor


@Composable
fun AddCardScreen(
    navController: NavController,
    viewModel: AddCardViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        if (state.error?.isNotEmpty() == true){
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = state.completed) {
        if (state.completed == true){
            navController.popBackStack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomTF(
            modifier = Modifier.testTag("card_number"),
            value = state.cardNumber,
            onValueChange = {viewModel.redactCardNumber(it)},
            hilt = "Номер карты",
            isPassword = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTF(
            modifier = Modifier.testTag("code"),
            value = state.code,
            onValueChange = {viewModel.redactCode(it)},
            hilt = "Код подтверждения",
            isPassword = false
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.addCard()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFFFFFFFF),
                containerColor = HyperColor
            )
        ) {
            Text(text = "Активировать")
        }
    }
}



