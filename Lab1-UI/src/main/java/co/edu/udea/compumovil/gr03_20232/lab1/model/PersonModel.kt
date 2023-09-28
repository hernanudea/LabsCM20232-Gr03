package co.edu.udea.compumovil.gr03_20232.lab1.model

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PersonModel : ViewModel() {
    private val _personState = MutableStateFlow(InfoViewState())
    val personState: StateFlow<InfoViewState> = _personState.asStateFlow()

    fun setFirstName(firstName: String) {
        _personState.update { currentState ->
            currentState.copy(
                firstName = firstName,
            )
        }
    }

    fun setLastName(lastName: String) {
        _personState.update { currentState ->
            currentState.copy(
                lastName = lastName,
            )
        }
    }

    fun setEmail(email: String) {
        _personState.update { currentState ->
            currentState.copy(
                email = email,
            )
        }
    }

    fun setAddress(address: String) {
        _personState.update { currentState ->
            currentState.copy(
                address = address,
            )
        }
    }

    fun setPhone(phone: String) {
        _personState.update { currentState ->
            currentState.copy(
                phone = phone,
            )
        }
    }

    fun setGender(gender: String) {
        _personState.update { currentState ->
            currentState.copy(
                gender = gender,
            )
        }
    }

    fun setBirthdate(birthdate: String) {
        _personState.update { currentState ->
            currentState.copy(
                birthdate = birthdate,
            )
        }
    }

    fun setScholarship(scholarship: String) {
        _personState.update { currentState ->
            currentState.copy(
                scholarship = scholarship,
            )
        }
    }

    fun setCountry(country: String) {
        Log.i("INF", country)
        _personState.update { currentState ->
            currentState.copy(
                country = country,
            )
        }
    }

    fun setCity(city: String) {
        Log.i("INF", city)
        _personState.update { currentState ->
            currentState.copy(
                city = city,
            )
        }
    }
}