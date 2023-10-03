package co.edu.udea.compumovil.gr03_20232.lab1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr03_20232.lab1.views.ContactDataActivityView
import co.edu.udea.compumovil.gr03_20232.lab1.views.HomePersonalDataActivityView

@Composable
fun NavManager() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomePersonalDataActivityView(navController)
        }
        composable("Contact", arguments = listOf()
//        composable("Contact/{appStateJson}", arguments = listOf(
//            navArgument("appStateJson") { type = NavType.StringType },
//            navArgument("opcional") { type = NavType.StringType }
//        )
        ) {
//            val appStateJson = it.arguments?.getString("appStateJson") ?: ""
//            val opcional = it.arguments?.getString("opcional") ?: ""
//            ContactDataActivityView(navController, appStateJson)
            ContactDataActivityView(navController)
        }
    }
}