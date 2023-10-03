package co.edu.udea.compumovil.gr03_20232.lab1.views

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr03_20232.lab1.R
import co.edu.udea.compumovil.gr03_20232.lab1.components.CustomButton
import co.edu.udea.compumovil.gr03_20232.lab1.components.CustomDatePicker
import co.edu.udea.compumovil.gr03_20232.lab1.components.CustomDropdown
import co.edu.udea.compumovil.gr03_20232.lab1.components.CustomOutlinedTextField
import co.edu.udea.compumovil.gr03_20232.lab1.components.CustomPersonIcon
import co.edu.udea.compumovil.gr03_20232.lab1.components.CustomRadioGroupSelectable
import co.edu.udea.compumovil.gr03_20232.lab1.components.SpaceH
import co.edu.udea.compumovil.gr03_20232.lab1.components.SpaceV
import co.edu.udea.compumovil.gr03_20232.lab1.components.TitleBar
import com.google.gson.Gson

var buttonIsActivePersonalData: Boolean = false

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePersonalDataActivityView(navController: NavController) {
    val appStateViewModel: AppStateViewModel = viewModel()
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
        ContentPersonalDataActivity(navController, isPortrait, appStateViewModel)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ContentPersonalDataActivity(
    navController: NavController,
    isPortrait: Boolean,
    appStateViewModel: AppStateViewModel
) {
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

    val mDate = rememberSaveable { mutableStateOf("") }

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
                value = appStateViewModel.name,
                onValueChange = {
                    appStateViewModel.name = it
                    validateRequiredFieldsPersonalData(
                        name = appStateViewModel.name,
                        lastName = appStateViewModel.lastName,
                        mDate = appStateViewModel.mDate
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false
                ),
                leadingIcon = { CustomPersonIcon() },
                label = stringResource(R.string.personal_data_names)
            )
            SpaceV()
            CustomOutlinedTextField(
                value = appStateViewModel.lastName,
                onValueChange = {
                    appStateViewModel.lastName = it
                    validateRequiredFieldsPersonalData(
                        name = appStateViewModel.name,
                        lastName = appStateViewModel.lastName,
                        mDate = appStateViewModel.mDate
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false
                ),
                leadingIcon = { CustomPersonIcon() },
                label = stringResource(R.string.personal_data_last_name)
            )
        } else {
            Row {
                CustomOutlinedTextField(
                    value = appStateViewModel.name,
                    onValueChange = {
                        appStateViewModel.name = it
                        validateRequiredFieldsPersonalData(
                            name = appStateViewModel.name,
                            lastName = appStateViewModel.lastName,
                            mDate = appStateViewModel.mDate
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = false
                    ),
                    leadingIcon = { CustomPersonIcon() },
                    label = stringResource(R.string.personal_data_names)
                )
                SpaceH()
                CustomOutlinedTextField(
                    value = appStateViewModel.lastName,
                    onValueChange = {
                        appStateViewModel.lastName = it
                        validateRequiredFieldsPersonalData(
                            name = appStateViewModel.name,
                            lastName = appStateViewModel.lastName,
                            mDate = appStateViewModel.mDate
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = false
                    ),
                    leadingIcon = { CustomPersonIcon() },
                    label = stringResource(R.string.personal_data_last_name)
                )
            }
        }

        SpaceV()

        CustomRadioGroupSelectable(
            items = sexOptionsList,
            selection = appStateViewModel.currentSelectionSex,
            onItemClick = { clickedItem ->
                appStateViewModel.currentSelectionSex = clickedItem
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

        CustomDatePicker(
            mDate = mDate,
            mYear = mYear,
            mMonth = mMonth,
            mDay = mDay,
            mCalendar = mCalendar,
            mContext = mContext
        ) { selectedDate ->
            appStateViewModel.mDate = selectedDate
            validateRequiredFieldsPersonalData(
                name = appStateViewModel.name,
                lastName = appStateViewModel.lastName,
                mDate = appStateViewModel.mDate
            )
        }
        SpaceV()

        CustomDropdown(
            label = stringResource(R.string.personal_data_level_schooling),
            items = itemsLevelSchooling,
            { newSelectedSchoolGradeOption ->
                appStateViewModel.selectedSchoolGrade = newSelectedSchoolGradeOption
            },
        )

        SpaceV()
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
            Spacer(modifier = Modifier.height(50.dp))
            CustomButton(
                isEnable = buttonIsActivePersonalData,
                name = stringResource(R.string.personal_data_next),
                backColor = MaterialTheme.colorScheme.primary,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
//                    .align(Alignment.BottomEnd)
                    .padding(25.dp)
            ) {
                val appStateJson = appStateViewModel.toJson()

                println("\n\n**************************************************")
                println(formatedStringPersonalData(
                    appStateViewModel.name,
                    appStateViewModel.lastName,
                    appStateViewModel.mDate,
                    appStateViewModel.currentSelectionSex,
                    appStateViewModel.selectedSchoolGrade
                ))
                println("**************************************************\n\n")
                println(appStateJson)
                println("**************************************************\n\n")

//                navController.navigate("Contact/${appStateJson}")
                navController.navigate("Contact")
            }
        }
    }
}

fun validateRequiredFieldsPersonalData(
    name: String,
    lastName: String,
    mDate: String
) {
    buttonIsActivePersonalData = (lastName.isNotBlank() && lastName.isNotEmpty()
            && name.isNotBlank() && name.isNotEmpty()
            && mDate.isNotBlank() && mDate.isNotEmpty()
            )
}


fun formatedStringPersonalData(
    name: String, lastName: String, mDate: String,
    currentSelectionSex: String, selectedSchoolGradeOption: String
): String {
    return String.format(
        """
        |Información personal:
        |$name $lastName
        |$currentSelectionSex
        |Nació el $mDate
        |Educación $selectedSchoolGradeOption
    """.trimMargin()
    )
}

fun AppStateViewModel.toJson(): String {
    val gson = Gson()
    return gson.toJson(this)
}