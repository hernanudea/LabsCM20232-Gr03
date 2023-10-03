package co.edu.udea.compumovil.gr03_20232.lab1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.edu.udea.compumovil.gr03_20232.lab1.views.ContactDataActivityView
import co.edu.udea.compumovil.gr03_20232.lab1.views.HomeDetailsActivityView
import co.edu.udea.compumovil.gr03_20232.lab1.views.HomePersonalDataActivityView

@Composable
fun NavManager() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomePersonalDataActivityView(navController)
        }
        composable(
            "Contact/{name}/", arguments = listOf(
                navArgument("name") { type = NavType.StringType },
            )
        ) {
            val name = it.arguments?.getString("name") ?: "0"
            ContactDataActivityView(navController, name)
        }
        composable("Details"){
            HomeDetailsActivityView(navController = navController)
        }
    }
}