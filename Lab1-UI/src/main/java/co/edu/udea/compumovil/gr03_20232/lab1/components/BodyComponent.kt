package co.edu.udea.compumovil.gr03_20232.lab1.components

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr03_20232.lab1.R
import java.util.Date


@Composable
fun SpaceH() {
    Spacer(modifier = Modifier.width(10.dp))
}

@Composable
fun SpaceV() {
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun CustomButton(
    isEnable: Boolean,
    name: String,
    backColor: Color,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            contentColor = color,
            containerColor = backColor
        ), enabled = isEnable,
        modifier = modifier
    ) {
        Text(text = name)
    }
}

@Composable
fun CustomPersonIcon() {
    Icon(imageVector = Icons.Default.Person, contentDescription = "Icon person")
}

@Composable
fun CustomIcon(icon: ImageVector, description: String) {
    Icon(imageVector = icon, contentDescription = description)
}

@Composable
fun CustomIcon(icon: Painter, description: String) {
    Icon(painter = icon, contentDescription = description)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier)
}

@Composable
fun CustomRadioButton(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions,
    leadingIcon: @Composable (() -> Unit)
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        label = { Text(text = label) }
    )
}

@Composable
fun CustomRadioGroupSelectable(
    items: List<String>,
    selection: String,
    onItemClick: (String) -> Unit,
    textlabel: String,
    icon: Painter,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .selectableGroup()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = icon, contentDescription = null)
        Text(text = textlabel)
        items.forEach { item ->
            CustomRadioButton(
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
fun CustomDatePicker(
    mDate: MutableState<String>,
    mYear: Int,
    mMonth: Int,
    mDay: Int,
    mCalendar: Calendar,
    mContext: Context,
    onDateSelected: (String) -> Unit
) {
    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
            onDateSelected(mDate.value)
        }, mYear, mMonth, mDay
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Row() {
                CustomIcon(
                    icon = Icons.Default.DateRange,
                    description = stringResource(R.string.personal_data_birthdate)
                )
                CustomText(text = stringResource(R.string.personal_data_birthdate))
            }
            CustomText(text = "${mDate.value}")
        }
        Spacer(modifier = Modifier.size(50.dp))
        CustomButton(
            isEnable = true,
            name = stringResource(R.string.personal_data_birthdate_change),
            backColor = MaterialTheme.colorScheme.primary,
            color = MaterialTheme.colorScheme.onPrimary,
        ) {
            mDatePickerDialog.show()
        }
    }
}

@Composable
fun CustomDropdown(
    label: String,
    items: List<String>,
    icon: Painter,
    onSelectedSchoolGradeOptionChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by rememberSaveable { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .absolutePadding(50.dp, 0.dp, 50.dp, 0.dp)
            .clickable(onClick = { expanded = true })
//            .background(Color.White)
            .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.extraSmall)
            .padding(6.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
//            CustomIcon(icon = painterResource(id = R.drawable.baseline_school_24), description = "")
            CustomIcon(icon = icon, description = "")
            Text(
                text = if (selected.toString().isEmpty()) items[0] else selected,
                modifier = Modifier.padding(4.dp).align(Alignment.CenterVertically)
            )
        }
    }

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
                enabled = !items[index].equals(label),
                onClick = {
                    expanded = false
                    selected = s
                    onSelectedSchoolGradeOptionChange(s)
                }
            )
        }
    }
}


