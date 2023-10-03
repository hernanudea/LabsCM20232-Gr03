package co.edu.udea.compumovil.gr03_20232.lab1.views

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr03_20232.lab1.R
import co.edu.udea.compumovil.gr03_20232.lab1.components.CustomButton
import co.edu.udea.compumovil.gr03_20232.lab1.components.CustomDropdown
import co.edu.udea.compumovil.gr03_20232.lab1.components.CustomOutlinedTextField
import co.edu.udea.compumovil.gr03_20232.lab1.components.CustomPersonIcon
import co.edu.udea.compumovil.gr03_20232.lab1.components.MainIconButton
import co.edu.udea.compumovil.gr03_20232.lab1.components.SpaceV
import co.edu.udea.compumovil.gr03_20232.lab1.components.TitleBar

var buttonIsActiveContactData: Boolean = false

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun ContactDataActivityView(navController: NavController, appStateJson: Any) {
fun ContactDataActivityView(navController: NavController) {
    val appStateViewModel: AppStateViewModel = viewModel()
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
        val isPortrait =
            LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
        ContentContactDataActivity(navController, isPortrait, appStateViewModel)
    }
}

@Composable
fun ContentContactDataActivity(
    navController: NavController,
    isPortrait: Boolean,
    appStateViewModel: AppStateViewModel
) {

    val countryList = listOf(
        stringResource(R.string.contact_data_country),
        stringResource(R.string.country_1),
        stringResource(R.string.country_2),
        stringResource(R.string.country_3),
        stringResource(R.string.country_4),
        stringResource(R.string.country_5),
        stringResource(R.string.country_6),
        stringResource(R.string.country_7),
        stringResource(R.string.country_8),
        stringResource(R.string.country_9)
    )
    val cityList = listOf(
        stringResource(R.string.contact_data_city),
        stringResource(R.string.city_1),
        stringResource(R.string.city_2),
        stringResource(R.string.city_3),
        stringResource(R.string.city_4),
        stringResource(R.string.city_5),
        stringResource(R.string.city_6),
        stringResource(R.string.city_7),
    )

//    var address by rememberSaveable { mutableStateOf("") }
//    var phone by rememberSaveable { mutableStateOf("") }
//    var email by rememberSaveable { mutableStateOf("") }
//    var selectedCountry by rememberSaveable { mutableStateOf("") }
//    var selectedCity by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        CustomOutlinedTextField(
            value = appStateViewModel.phone,
            onValueChange = {
                appStateViewModel.phone = it
                validateRequiredFieldsContactData(
                    phone = appStateViewModel.phone,
                    email = appStateViewModel.email,
                    country = appStateViewModel.selectedCountry
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next,
                autoCorrect = false
            ),
            leadingIcon = { CustomPersonIcon() },
            label = stringResource(R.string.contact_data_phone)
        )

        SpaceV()
        CustomOutlinedTextField(
            value = appStateViewModel.email,
            onValueChange = {
                appStateViewModel.email = it
                validateRequiredFieldsContactData(
                    phone = appStateViewModel.phone,
                    email = appStateViewModel.email,
                    country = appStateViewModel.selectedCountry
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = false
            ),
            leadingIcon = { CustomPersonIcon() },
            label = stringResource(R.string.contact_data_email)
        )
        SpaceV()
        CustomDropdown(
            label = stringResource(R.string.contact_data_country),
            items = countryList
        ) { newSelectedCountry ->
            appStateViewModel.selectedCountry = newSelectedCountry
            validateRequiredFieldsContactData(
                phone = appStateViewModel.phone,
                email = appStateViewModel.email,
                country = appStateViewModel.selectedCountry
            )
        }
        SpaceV()
        CustomDropdown(
            label = stringResource(R.string.contact_data_city),
            items = cityList
        ) { newSelectedCity ->
            appStateViewModel.selectedCity = newSelectedCity
        }
        SpaceV()
        CustomOutlinedTextField(
            value = appStateViewModel.address,
            onValueChange = {
                appStateViewModel.address = it;
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = false
            ),
            leadingIcon = { CustomPersonIcon() },
            label = stringResource(R.string.contact_data_address)
        )
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
            CustomButton(
                isEnable = buttonIsActiveContactData,
                name = stringResource(R.string.personal_data_send),
                backColor = MaterialTheme.colorScheme.primary,
                color = MaterialTheme.colorScheme.onPrimary
            ) {
                println("\n\n**************************************************")
                println(formatedStringContactData(
                    phone = appStateViewModel.phone,
                    address = appStateViewModel.address,
                    email = appStateViewModel.email,
                    country = appStateViewModel.selectedCountry,
                    city = appStateViewModel.selectedCity))
                println("\n\n**************************************************")

//            navController.navigate("Details")
            }
        }
    }
}

fun validateRequiredFieldsContactData(phone: String, email: String, country: String) {
    buttonIsActiveContactData = (email.isNotBlank() && email.isNotEmpty()
            && phone.isNotBlank() && phone.isNotEmpty()
            && country.isNotBlank() && country.isNotEmpty()
            )
}

fun formatedStringContactData(
    phone: String, address: String, email: String,
    country: String, city: String
): String {
    return String.format(
        """
    |Información de contacto:
    |Teléfono:  $phone
    |Dirección: $address
    |Email:     $email
    |País:      $country
    |Ciudad:    $city.
    """.trimMargin()
    )
}