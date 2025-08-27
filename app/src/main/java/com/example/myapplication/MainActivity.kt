package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.data.model.Character
import com.example.myapplication.nav.Route
import com.example.myapplication.ui.CharactersViewModel
import com.example.myapplication.ui.screens.CharacterDetailScreen
import com.example.myapplication.ui.screens.CharacterListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Puedes envolver con tu tema si tienes uno (e.g., MyApplicationTheme { ... })
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val nav = rememberNavController()
                    val vm: CharactersViewModel = viewModel()

                    NavHost(
                        navController = nav,
                        startDestination = Route.List.route
                    ) {
                        composable(Route.List.route) {
                            CharacterListScreen(
                                state = vm.state,
                                onClick = { c -> nav.navigate(Route.Detail.pass(c)) }
                            )
                        }
                        composable(
                            route = Route.Detail.route,
                            arguments = listOf(navArgument("character") { type = NavType.StringType })
                        ) { backStack ->
                            val encoded = backStack.arguments!!.getString("character")!!
                            val c: Character = Route.Detail.read(encoded)
                            CharacterDetailScreen(character = c)
                        }
                    }
                }
            }
        }
    }
}
