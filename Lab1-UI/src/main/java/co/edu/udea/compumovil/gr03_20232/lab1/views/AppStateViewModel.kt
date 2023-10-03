package co.edu.udea.compumovil.gr03_20232.lab1.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AppStateViewModel : ViewModel() {
    var name by mutableStateOf("")
    var lastName by mutableStateOf("")
    var mDate by mutableStateOf("")
    var selectedSchoolGrade by mutableStateOf("")
    var currentSelectionSex by mutableStateOf("")

    var address by mutableStateOf("")
    var phone by mutableStateOf("")
    var email by mutableStateOf("")
    var selectedCountry by mutableStateOf("")
    var selectedCity by mutableStateOf("")
}
