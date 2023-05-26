package com.example.client_android.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun InputView(text: String, onTextChanged: (String) -> Unit, title: String, placeholder: String, isSecureField: Boolean = false) {
    Column {
        Text(
            text = title,
            color = Color.DarkGray,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            modifier = Modifier.fillMaxWidth()
        )

        if (isSecureField) {
            val passwordState = remember { mutableStateOf(text) }
            TextField(
                value = passwordState.value,
                onValueChange = {
                    passwordState.value = it
                    onTextChanged(it)
                },
                placeholder = { Text(text = placeholder) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            TextField(
                value = text,
                onValueChange = { onTextChanged(it) },
                placeholder = { Text(text = placeholder) },
                textStyle = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Divider()

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview
@Composable
fun InputViewPreview() {
    InputView(
        text = "",
        onTextChanged = { },
        title = "Email Address",
        placeholder = "name@example.com"
    )
}
