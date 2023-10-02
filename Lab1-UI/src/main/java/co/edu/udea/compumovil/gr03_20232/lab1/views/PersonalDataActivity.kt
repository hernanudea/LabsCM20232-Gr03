package co.edu.udea.compumovil.gr03_20232.lab1.views

import android.annotation.SuppressLint
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr03_20232.lab1.R
import co.edu.udea.compumovil.gr03_20232.lab1.components.CustomOutlinedTextField
import co.edu.udea.compumovil.gr03_20232.lab1.components.DropdownDemo
import co.edu.udea.compumovil.gr03_20232.lab1.components.MainButton
import co.edu.udea.compumovil.gr03_20232.lab1.components.MainDatePicker
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
@Composable
fun ContentPersonalDataActivity(navController: NavController) {
    val sexOptionsList = listOf(
        stringResource(R.string.personal_data_sex_men),
        stringResource(R.string.personal_data_sex_woman)
    )

    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    val mDate = rememberSaveable { mutableStateOf("") }
    var selectedName by rememberSaveable { mutableStateOf("") }
    var selectedSchoolGradeOption by rememberSaveable { mutableStateOf("") }
    var currentSelection by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        CustomOutlinedTextField(
            value = name,
            onValueChange = { name = it; validateRequiredFields(name, lastName, mDate) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = false
            ),
            leadingIcon = { PersonIcon() },
            label = stringResource(R.string.personal_data_names)
        )
        Space()
        CustomOutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it; validateRequiredFields(name, lastName, mDate) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = false
            ),
            leadingIcon = { PersonIcon() },
            label = stringResource(R.string.personal_data_last_name)
        )
        Space()
        val painter = painterResource(id = R.drawable.baseline_gender_24)
        RadioGroupWithSelectable(
            items = sexOptionsList,
            selection = currentSelection,
            onItemClick = { clickedItem ->
                currentSelection = clickedItem
            },
            stringResource(R.string.personal_data_sex),
            icon = painter,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        Space()
        val mContext = LocalContext.current
        val mCalendar = Calendar.getInstance()

        val mYear = mCalendar.get(Calendar.YEAR)
        val mMonth = mCalendar.get(Calendar.MONTH)
        val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

        MainDatePicker(
            mDate = mDate,
            mYear = mYear,
            mMonth = mMonth,
            mDay = mDay,
            mCalendar = mCalendar,
            mContext = mContext
        ) { selectedDate ->
            validateRequiredFields(name, lastName, mDate)
        }
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
            println("currentSelection: ${currentSelection}")
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

