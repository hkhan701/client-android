package com.example.client_android.core.authentication.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.client_android.components.InputView
import com.example.client_android.core.authentication.viewModels.AuthViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginView(
    navController: NavController,
    viewModel: AuthViewModel = getViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    ) {
        // Image or logo
        // ...

        Column(modifier = Modifier.padding(top = 16.dp)) {
            InputView(
                text = email,
                onTextChanged = { email = it },
                title = "Email Address",
                placeholder = "name@example.com"
            )
            InputView(
                text = password,
                onTextChanged = { password = it },
                title = "Password",
                placeholder = "Enter your password",
                isSecureField = true
            )
        }

        Button(
            onClick = {
                viewModel.signIn(email, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "SIGN IN",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Don't have an account? Sign up",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable { navController.navigate("signup") },
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun LoginViewPreview() {
    val navController = rememberNavController()

    LoginView(navController = navController)
}