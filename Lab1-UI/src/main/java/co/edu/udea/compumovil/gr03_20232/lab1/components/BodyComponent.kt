package co.edu.udea.compumovil.gr03_20232.lab1.components

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr03_20232.lab1.R
import co.edu.udea.compumovil.gr03_20232.lab1.views.validateRequiredFields
import java.util.Date


@Composable
fun TitleView(name: String) {
    Text(
        text = name,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun Space() {
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun MainButton(isEnable: Boolean, name: String, backColor: Color, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            contentColor = color,
            containerColor = backColor
        ), enabled = isEnable
    ) {
        Text(text = name)
    }
}

@Composable
fun PersonIcon() {
    Icon(imageVector = Icons.Default.Person, contentDescription = "Icon person")
}

@Composable
fun MainIcon(icon: ImageVector, description: String) {
    Icon(imageVector = icon, contentDescription = description)
}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainOutlinedTextField(valueName: String, icon: Icons, label: String, valueChange: String){
//    OutlinedTextField(
//        value = valueName,
//        onValueChange = valueChange,
//        leadingIcon = icon,
//        label = MainText(text = label))
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainText(text: String) {
//    OutlinedTextField(value = "sss", onValueChange = {s: String ->  println(s) })
    Text(text = text)
}

@Composable
fun LabelledRadioButton(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: (() -> Unit)?,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors()
) {

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            colors = colors
        )

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.merge(),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun RadioGroupWithSelectable(
    modifier: Modifier,
    items: List<String>,
    selection: String,
    onItemClick: ((String) -> Unit),
    textlabel: String
) {
    Row(modifier = modifier.selectableGroup()) {
        Text(text = textlabel)
        items.forEach { item ->
            LabelledRadioButton(
                modifier = Modifier
                    .selectable(
                        selected = item == selection,
                        onClick = { onItemClick(item) },
                        role = Role.RadioButton
                    ),
                label = item,
                selected = item == selection,
                onClick = null
            )
        }
    }
}

@Composable
fun MainDatePicker(
    mDate: MutableState<String>,
    mYear: Int,
    mMonth: Int,
    mDay: Int,
    mCalendar: Calendar,
    mContext: Context
) {
    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                MainIcon(
                    icon = Icons.Default.DateRange, description = stringResource(R.string.personal_data_birthdate)
                )
                MainText(text = stringResource(R.string.personal_data_birthdate))
            }
            MainText(text = "${mDate.value}")
        }
        Spacer(modifier = Modifier.size(50.dp))
        MainButton(isEnable = true,
            name = stringResource(R.string.personal_data_birthdate_change),
            backColor = MaterialTheme.colorScheme.primary,
            color = MaterialTheme.colorScheme.onPrimary
        ) {
            mDatePickerDialog.show()
        }
    }
}

@Composable
fun DropdownDemo(
    selectedName: MutableState<String>,
    items: List<String>,
    onSelectedSchoolGradeOptionChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Text(
        if (selectedName.value.isEmpty()) items[0] else selectedName.value,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { expanded = true })
            .background(Color.White)
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        items.forEachIndexed { index, s ->
            DropdownMenuItem(
                text = { Text(items[index]) },
                enabled = !items[index].equals(stringResource(R.string.personal_data_level_schooling)),
                onClick = {
                    expanded = false
                    selectedName.value = s
                    onSelectedSchoolGradeOptionChange(s)
                }
            )
        }
    }
}


