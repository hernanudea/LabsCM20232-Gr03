package co.edu.udea.compumovil.gr03_20232.lab1.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr03_20232.lab1.R
import co.edu.udea.compumovil.gr03_20232.lab1.components.MainButton
import co.edu.udea.compumovil.gr03_20232.lab1.components.MainIconButton
import co.edu.udea.compumovil.gr03_20232.lab1.components.Space
import co.edu.udea.compumovil.gr03_20232.lab1.components.TitleBar
import co.edu.udea.compumovil.gr03_20232.lab1.components.TitleView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDataActivityView(navController: NavController, id: String, optional: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleBar(name = stringResource(R.string.contact_data_title)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        ContentContactDataActivity(navController, id, optional)
    }
}

@Composable
fun ContentContactDataActivity(navController: NavController, name: String, lastName: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        TitleView(name = "Details View")
//        Space()
        TitleView(name = name)
        Space()
        if (lastName==""){
            Spacer(modifier = Modifier.height(0.dp))
        }else{
            TitleView(name = lastName.orEmpty())
        }
//        MainButton(isEnable = true, name = stringResource(R.string.contact_data_go_back_to),
//            backColor = MaterialTheme.colorScheme.primary, color = MaterialTheme.colorScheme.onPrimary) {
//            navController.popBackStack()
//        }
        MainButton(isEnable = true,
            name = stringResource(R.string.personal_data_next),
            backColor = MaterialTheme.colorScheme.primary,
            color = MaterialTheme.colorScheme.onPrimary
        ) {
            navController.navigate("Details")
        }
    }

}