package com.example.qwerty.presentation.account.Authorization

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.qwerty.R
import com.example.qwerty.presentation.common.CustomTF
import com.example.qwerty.presentation.navigation.NavRoutes
import com.example.qwerty.presentation.ui.theme.Cyan


@Composable
fun LogInScreen (navController: NavController, viewModel: LogInViewModel = hiltViewModel()){
    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        if (state.error.isNotEmpty()){
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(key1 = state.isComplete){
        if (state.isComplete){
            navController.navigate(NavRoutes.MainNav.route)
        }
    }

    Box(){
        Image(
            painter = painterResource(id = R.drawable.signup),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Вход",style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(16.dp))

            CustomTF(
                value = state.email,
                onValueChange = { viewModel.change_email(it) },
                hilt = "Электронная почта"
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomTF(value = state.password,
                onValueChange = {viewModel.change_password(it)},
                hilt = "Пароль",
                true)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.log_in()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Войти")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(text = "Ещё не зарегестрированы? ")
                Text(
                    text = "SignUp",
                    color = Cyan,
                    modifier = Modifier.clickable {
                        navController.navigate(NavRoutes.SignUpNav.route){
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}