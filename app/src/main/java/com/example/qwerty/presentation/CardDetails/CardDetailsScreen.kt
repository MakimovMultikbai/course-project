package com.example.qwerty.presentation.CardDetails


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.qwerty.R
import com.example.qwerty.domain.models.Card
import com.example.qwerty.domain.models.TransactionData
import com.example.qwerty.presentation.app.CardItem
import com.example.qwerty.presentation.ui.theme.HyperColor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun CardDetailsScreen(
    navController: NavController,
    viewModel: CardDetailsViewModel = hiltViewModel(),
    card: Card
) {
    val state = viewModel.state.value
    val transactions = viewModel.transactions
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getData(card)
    }


    LaunchedEffect(key1 = state.completed) {
        if (state.completed){
            navController.popBackStack()
        }
    }


    LaunchedEffect(key1 = state.error) {
        if (state.error?.isNotEmpty() == true){
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }


    IconButton(
        onClick = {
            navController.popBackStack()
        },
        modifier = Modifier
            .padding(10.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.back),
            modifier = Modifier.size(32.dp),
            tint = Color.Black,
            contentDescription = null
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn {
            item {
                CardItem(state.card?:Card()){}

                Button(
                    onClick = {
                        viewModel.block()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color(0xFFFFFFFF),
                        containerColor = HyperColor
                    )
                ) {
                    Text(text = "Блокировать")
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.freeze()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color(0xFFFFFFFF),
                        containerColor = HyperColor
                    )
                ) {
                    Text(text = "Заморозить")
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.unfreeze()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color(0xFFFFFFFF),
                        containerColor = HyperColor
                    )
                ) {
                    Text(text = "Разморозить")
                }

                Spacer(Modifier.height(32.dp))
            }
            itemsIndexed(transactions){ index, item ->
                TransactionItem(item)
                Spacer(Modifier.height(8.dp))
            }
        }

    }
}

@Composable
fun TransactionItem(
    transactionData: TransactionData
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(dateToDateString(transactionData.createdAt))
        Text(transactionData.total.toString() + "₽")
        Text(if (transactionData.isPurchase) "Покупка" else "Возврат")
    }
}


fun dateToDateString(date: Date): String{
    val formatter = SimpleDateFormat("d MMMM, HH:mm", Locale("ru"))
    return formatter.format(date)
}



