package com.appdevelopment.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appdevelopment.unitconverter.ui.theme.UnitConverterTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   unitconverter()
                }
            }
        }
    }
}

@Composable
fun unitconverter()
{
    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit by remember{ mutableStateOf("Meter") }
    var outputunit by remember{ mutableStateOf("Meter") }
    var iexpand by remember{ mutableStateOf(false) }
    var oexpand by remember {
        mutableStateOf(false)
    }

    var conversionfact by remember{ mutableStateOf(1.00) }
    var oconversionfact by remember {
        mutableStateOf(1.00)
    }

    fun unitconvt()
    {
        var inputdouble = inputvalue.toDoubleOrNull()?:0.0
        var result = (inputdouble * conversionfact * 100/oconversionfact).roundToInt()/100.0
        outputvalue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", fontSize = 22.sp)
        Spacer(modifier = Modifier.size(25.dp))
        OutlinedTextField(value = inputvalue,
            onValueChange = {
            inputvalue = it
        }, label = { Text(text = "Enter a value")})
        Spacer(modifier = Modifier.size(25.dp))
        Row {
            Box{
                //Input
                Button(onClick = {
                    iexpand = true
                }) {
                    Text(text = inputunit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "")
                }
                DropdownMenu(expanded = iexpand, onDismissRequest = { iexpand=false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter")}, onClick = {
                        iexpand=false
                        inputunit = "Centimeter"
                        conversionfact = 0.01
                        unitconvt()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = { iexpand=false
                        inputunit = "Feet"
                        conversionfact = 0.3048
                        unitconvt() })
                    DropdownMenuItem(text = { Text(text = "Milimeter") }, onClick = { iexpand=false
                        inputunit = "Milimeter"
                        conversionfact = 0.001
                        unitconvt() })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        iexpand=false
                        inputunit = "Meter"
                        conversionfact = 1.0
                        unitconvt() })
                }
            }
            Spacer(modifier = Modifier.size(25.dp))
            Box{
                //Output
                Button(onClick = {
                    oexpand=true
                }) {
                    Text(text = outputunit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "")
                }
                DropdownMenu(expanded = oexpand, onDismissRequest = { oexpand = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter")}, onClick = {
                        oexpand=false
                        outputunit = "Centimeter"
                        oconversionfact = 0.01
                        unitconvt() })
                    DropdownMenuItem(text = { Text(text = "Foot")}, onClick = { oexpand=false
                        outputunit = "Foot"
                        oconversionfact =0.3048
                        unitconvt() })
                    DropdownMenuItem(text = { Text(text = "Milimeter") }, onClick = { oexpand=false
                        outputunit = "Milimeter"
                        oconversionfact = 0.001
                        unitconvt() })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = { oexpand=false
                        outputunit = "Meter"
                        oconversionfact = 1.0
                        unitconvt()})
                }
            }
        }
        Spacer(modifier = Modifier.size(15.dp))
        Text(text = "Result = ${outputvalue}")
    }
}

@Preview(showBackground = true)
@Composable
fun unitconverterPreview() {
    unitconverter()
}