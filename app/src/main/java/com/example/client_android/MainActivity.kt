package com.example.client_android

import com.example.client_android.core.authentication.views.LoginView
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.client_android.app.AppwriteClient
import com.example.client_android.core.authentication.viewModels.AuthViewModel
import com.example.client_android.core.authentication.views.SignupView
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            Navigation()
        }
    }
}

val appModule = module {
    single { AppwriteClient(androidContext()) }
    single<SharedPreferences> {
        androidContext().getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
    }

    viewModel { AuthViewModel(get(), get()) }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginView(navController)
        }
        composable("signup") {
            SignupView(navController)
        }
    }
}

