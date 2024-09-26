package com.example.qwerty.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qwerty.R

@Composable
fun CustomTF (value: String, onValueChange: (String) -> Unit, hilt: String, isPassword: Boolean = false) {
    var showPass  by remember {mutableStateOf(false)}
    BasicTextField(
    visualTransformation =
    if (isPassword) {
        if (showPass) VisualTransformation.None
        else PasswordVisualTransformation('*')
    }
        else VisualTransformation.None,
    value = value,
    onValueChange = onValueChange,
    modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(15.dp))
        .background(Color.LightGray, RoundedCornerShape(15.dp))
        .height(50.dp),
    textStyle = TextStyle(),
    decorationBox = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
            .padding(horizontal = 16.dp),
        ) {

            Box(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(end = 12.dp),
                contentAlignment = Alignment.CenterStart
            ){
                if (value.isEmpty())  {
                    Text(text = hilt)
                }
                it()

            }

            if (isPassword){
            Image(
                painter = painterResource(id =
                        if (showPass) R.drawable.hide_pass
                        else R.drawable.show_pass
                ),
                contentDescription = null,
                Modifier.size(40.dp)
                    .clickable {
                        showPass = !showPass
                    }
            )
            }
        }
    }

    )
}

@Preview
@Composable
fun qw() {
    CustomTF(value = "", onValueChange = {}, hilt = "1123")
}