package co.edu.udea.compumovil.gr03_20232.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr03_20232.lab1.navigation.NavManager
import co.edu.udea.compumovil.gr03_20232.lab1.ui.theme.LabsCM20232Gr03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsCM20232Gr03Theme {
                NavManager()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabsCM20232Gr03Theme {
        NavManager()
    }
}