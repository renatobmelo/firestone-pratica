package com.example.navegacao1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navegacao1.ui.telas.TelaCadastro
import com.example.navegacao1.ui.telas.TelaLogin
import com.example.navegacao1.ui.telas.TelaPrincipal
import com.example.navegacao1.ui.theme.Navegacao1Theme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            Navegacao1Theme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = backStackEntry?.destination

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = when (currentDestination?.route) {
                                        "login" -> "Login"
                                        "cadastro" -> "Cadastro"
                                        "principal" -> "Principal"
                                        else -> "App"
                                    }
                                )
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            TelaLogin(modifier = Modifier.padding(innerPadding), onSigninClick = {
                                navController.navigate("principal")
                            }, onSignupClick = {
                                navController.navigate("cadastro")
                            })
                        }
                        composable("cadastro") {
                            TelaCadastro(modifier = Modifier.padding(innerPadding), onCadastroClick = {
                                navController.navigate("login")
                            })
                        }
                        composable("principal") {
                            TelaPrincipal(modifier = Modifier.padding(innerPadding), onLogoffClick = {
                                navController.navigate("login")
                            })
                        }
                    }
                }
            }
        }
    }
}
