package co.edu.udea.compumovil.gr03_20232.lab1.views

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr03_20232.lab1.components.ActionButton
import co.edu.udea.compumovil.gr03_20232.lab1.components.RadioGroupWithSelectable
import co.edu.udea.compumovil.gr03_20232.lab1.components.Space
import co.edu.udea.compumovil.gr03_20232.lab1.components.TitleBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePersonalDataActivityView(navController: NavController) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { TitleBar(name = "Información Personal") }, // ToDo
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Blue
            )
        )
    }, floatingActionButton = {
        ActionButton()
    }
    ) {
        ContentPersonalDataActivity(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentPersonalDataActivity(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Nombres") })
        Space()
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(text = "Apellidos") })
        Space()
        val SexOptions = listOf("Hombre", "Mujer")
        val currentSelection = remember { mutableStateOf(SexOptions.first()) }

        RadioGroupWithSelectable(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            items = SexOptions,
            selection = currentSelection.value,
            onItemClick = { clickedItem ->
                currentSelection.value = clickedItem
            },
            "Sexo:"
        )
        Space()


        //        MainButton(name = "Detail View", backColor = Color.Blue, color = Color.White) {
//            navController.navigate("Details/${id}/?${name}")
//        }
    }
}