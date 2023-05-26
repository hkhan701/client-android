package com.example.client_android.core.authentication.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.client_android.components.InputView
import com.example.client_android.core.authentication.viewModels.AuthViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun SignupView(
    navController: NavController,
    viewModel: AuthViewModel = getViewModel()
) {
    var email by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // Image or logo to add for future

        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        ) {
            InputView(
                text = email,
                onTextChanged = { email = it },
                title = "Email Address",
                placeholder = "name@example.com"
            )
            InputView(
                text = fullname,
                onTextChanged = { fullname = it },
                title = "Full Name",
                placeholder = "Enter your name"
            )
            InputView(
                text = password,
                onTextChanged = { password = it },
                title = "Password",
                placeholder = "Enter your password",
                isSecureField = true
            )
            InputView(
                text = confirmPassword,
                onTextChanged = { confirmPassword = it },
                title = "Confirm Password",
                placeholder = "Confirm your password",
                isSecureField = true
            )
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.createUser(email, password, fullname)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "SIGN UP",
//                    style = MaterialTheme.typography.button,
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

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Already have an account?",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Sign in",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun SignupViewPreview() {
    val navController = rememberNavController()

    SignupView(navController = navController)
}