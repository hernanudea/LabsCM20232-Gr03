package co.edu.udea.compumovil.gr03_20232.lab1.views

import android.annotation.SuppressLint
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr03_20232.lab1.R
import co.edu.udea.compumovil.gr03_20232.lab1.components.DropdownDemo
import co.edu.udea.compumovil.gr03_20232.lab1.components.MainButton
import co.edu.udea.compumovil.gr03_20232.lab1.components.MainDatePicker
import co.edu.udea.compumovil.gr03_20232.lab1.components.MainIcon
import co.edu.udea.compumovil.gr03_20232.lab1.components.PersonIcon
import co.edu.udea.compumovil.gr03_20232.lab1.components.RadioGroupWithSelectable
import co.edu.udea.compumovil.gr03_20232.lab1.components.Space
import co.edu.udea.compumovil.gr03_20232.lab1.components.TitleBar

var buttonIsActive: Boolean = false

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePersonalDataActivityView(navController: NavController) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { TitleBar(name = stringResource(R.string.personal_data_title)) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }
    ) {
        ContentPersonalDataActivity(navController)
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentPersonalDataActivity(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    val mDate = remember { mutableStateOf("") }
    var selectedName by remember { mutableStateOf("") }
    var selectedSchoolGradeOption by mutableStateOf("")


    val sexOptions = listOf(
        stringResource(R.string.personal_data_sex_men),
        stringResource(R.string.personal_data_sex_woman)
    )
    val currentSelection = remember { mutableStateOf(sexOptions.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it; validateRequiredFields(name, lastName, mDate) },
            leadingIcon = { PersonIcon() },
            label = { Text(text = stringResource(R.string.personal_data_names)) }
        )
        Space()
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it; validateRequiredFields(name, lastName, mDate) },
            leadingIcon = { PersonIcon() },
//            leadingIcon = { Icons.Default.Person },
            label = { Text(text = stringResource(R.string.personal_data_last_name)) })
        Space()
        Row {
            MainIcon(
                icon = Icons.Default.AccountCircle,
                description = stringResource(R.string.personal_data_sex)
            )
            RadioGroupWithSelectable(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                items = sexOptions,
                selection = currentSelection.value,
                onItemClick = { clickedItem ->
                    currentSelection.value = clickedItem
                },
                stringResource(R.string.personal_data_sex)

            )

        }

        Space()

        val mContext = LocalContext.current
        val mCalendar = Calendar.getInstance()

        val mYear = mCalendar.get(Calendar.YEAR)
        val mMonth = mCalendar.get(Calendar.MONTH)
        val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
        MainDatePicker(mDate, mYear, mMonth, mDay, mCalendar, mContext)

        Space()

        val itemsDropDown = listOf(
            stringResource(R.string.personal_data_level_schooling),
            stringResource(R.string.personal_data_level_schooling_pri),
            stringResource(R.string.personal_data_level_schooling_sec),
            stringResource(R.string.personal_data_level_schooling_uni),
            stringResource(R.string.personal_data_level_schooling_oth)
        )
        Space()

        DropdownDemo(
            selectedName = mutableStateOf(selectedName),
            items = itemsDropDown
        ) { newSelectedSchoolGradeOption ->
            selectedSchoolGradeOption = newSelectedSchoolGradeOption
        }

        Space()

        MainButton(
            isEnable = buttonIsActive,
            name = stringResource(R.string.personal_data_next),
            backColor = MaterialTheme.colorScheme.primary,
            color = MaterialTheme.colorScheme.onPrimary
        ) {
            println("\n\n**************************************************")
            println("name: ${name}")
            println("lastName: ${lastName}")
            println("currentSelection: ${currentSelection.value}")
            println("mDate: ${mDate.value}")
            println("selectedSchoolGradeOption: ${selectedSchoolGradeOption}")
            println("**************************************************\n\n")
            navController.navigate("Contact/${name}/?${lastName}")
        }

    }
}

fun validateRequiredFields(name: String, lastName: String, mDate: MutableState<String>) {
    buttonIsActive = (lastName.isNotBlank() && lastName.isNotEmpty()
            && name.isNotBlank() && name.isNotEmpty()
            && mDate.value.isNotBlank() && mDate.value.isNotEmpty()
            )
}
