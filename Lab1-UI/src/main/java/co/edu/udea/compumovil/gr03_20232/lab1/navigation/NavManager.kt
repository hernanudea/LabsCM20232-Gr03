package co.edu.udea.compumovil.gr03_20232.lab1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.edu.udea.compumovil.gr03_20232.lab1.views.ContactDataActivityView
import co.edu.udea.compumovil.gr03_20232.lab1.views.HomePersonalDataActivityView

@Composable
fun NavManager() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomePersonalDataActivityView(navController)
        }
        composable(
            "Details/{id}/?{optional}", arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("optional") { type = NavType.StringType },
            )
        ) {
            val id = it.arguments?.getString("id") ?: "0"
            val optional = it.arguments?.getString("optional") ?: ""
            ContactDataActivityView(navController, id, optional)
        }
    }
}