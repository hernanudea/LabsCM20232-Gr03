package co.edu.udea.compumovil.gr03_20232.lab1.views

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalConfiguration
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
import co.edu.udea.compumovil.gr03_20232.lab1.components.SpaceH
import co.edu.udea.compumovil.gr03_20232.lab1.components.SpaceV
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
        val isPortrait =
            LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
        ContentPersonalDataActivity(navController, isPortrait)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ContentPersonalDataActivity(navController: NavController, isPortrait: Boolean) {
    val sexOptionsList = listOf(
        stringResource(R.string.personal_data_sex_men),
        stringResource(R.string.personal_data_sex_woman)
    )

    val itemsLevelSchooling = listOf(
        stringResource(R.string.personal_data_level_schooling),
        stringResource(R.string.personal_data_level_schooling_pri),
        stringResource(R.string.personal_data_level_schooling_sec),
        stringResource(R.string.personal_data_level_schooling_uni),
        stringResource(R.string.personal_data_level_schooling_oth)
    )

    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    val mDate = rememberSaveable { mutableStateOf("") }
    var selectedName by rememberSaveable { mutableStateOf("") }
    var selectedSchoolGradeOption by rememberSaveable { mutableStateOf("") }
    var currentSelectionSex by rememberSaveable { mutableStateOf("") }

    val painter = painterResource(id = R.drawable.baseline_gender_24)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        if (isPortrait) {
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
            SpaceV()
            CustomOutlinedTextField(
                value = lastName,
                onValueChange = {
                    lastName = it; validateRequiredFields(
                    name,
                    lastName,
                    mDate
                )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false
                ),
                leadingIcon = { PersonIcon() },
                label = stringResource(R.string.personal_data_last_name)
            )
        } else {
            Row {
                CustomOutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it; validateRequiredFields(
                        name,
                        lastName,
                        mDate
                    )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = false
                    ),
                    leadingIcon = { PersonIcon() },
                    label = stringResource(R.string.personal_data_names)
                )
                SpaceH()
                CustomOutlinedTextField(
                    value = lastName,
                    onValueChange = {
                        lastName = it; validateRequiredFields(
                        name,
                        lastName,
                        mDate
                    )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = false
                    ),
                    leadingIcon = { PersonIcon() },
                    label = stringResource(R.string.personal_data_last_name)
                )
            }
        }

        SpaceV()

        RadioGroupWithSelectable(
            items = sexOptionsList,
            selection = currentSelectionSex,
            onItemClick = { clickedItem ->
                currentSelectionSex = clickedItem
            },
            stringResource(R.string.personal_data_sex),
            icon = painter,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        SpaceV()
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
        SpaceV()

        DropdownDemo(
            selectedName = mutableStateOf(selectedName),
            items = itemsLevelSchooling
        ) { newSelectedSchoolGradeOption ->
            selectedSchoolGradeOption = newSelectedSchoolGradeOption
        }

        SpaceV()
        Box(modifier = Modifier.fillMaxWidth()) {
            MainButton(
                isEnable = buttonIsActive,
                name = stringResource(R.string.personal_data_next),
                backColor = MaterialTheme.colorScheme.primary,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(25.dp)
            ) {
                val summaryPersonalData = formatedString(
                    name, lastName, mDate.value, currentSelectionSex,
                    selectedSchoolGradeOption
                ).replace(" ", "-")
                println("\n\n**************************************************")
                println(summaryPersonalData)
                println("**************************************************\n\n")

//                navController.navigate("ContactData/${summaryPersonalData}/")
                navController.navigate("Contact/${name}/")
            }
        }
    }
}

fun validateRequiredFields(name: String, lastName: String, mDate: MutableState<String>) {
    buttonIsActive = (lastName.isNotBlank() && lastName.isNotEmpty()
            && name.isNotBlank() && name.isNotEmpty()
            && mDate.value.isNotBlank() && mDate.value.isNotEmpty()
            )
}


fun formatedString(
    name: String, lastName: String, mDate: String,
    currentSelectionSex: String, selectedSchoolGradeOption: String
): String {
//    return String.format("""
//        |{
//        |personalInfo: {name: $name,
//        |lastName: $lastName,
//        |sex: $currentSelectionSex,
//        |birthdate: $mDate,
//        |schoolGrade: $selectedSchoolGradeOption
//        |}
//        |}
//""".trimMargin())
    return String.format("""
    |Name:         $name
    |Last Name:    $lastName
    |Sex:          $currentSelectionSex
    |Birthdate:    $mDate
    |School Grade: $selectedSchoolGradeOption
    """.trimMargin()
    )
}

