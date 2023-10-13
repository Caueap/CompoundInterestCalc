package com.example.compoundinterestcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compoundinterestcalc.ui.theme.CompoundInterestCalcTheme
import java.text.NumberFormat
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompoundInterestCalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {

    var inputAmount by remember {
        mutableStateOf("")
    }

    var inputMonths by remember {
        mutableStateOf("")
    }

    var totalAmount by remember {
        mutableStateOf("")
    }

    val amount = inputAmount.toDoubleOrNull()
    val months = inputMonths.toDoubleOrNull()

    Column(
        modifier = Modifier
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Informe o valor do seu investimento",
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = inputAmount,
            onValueChange = { inputAmount = it },
            label = {
                Text(
                    text = "R$: 0,00"
                )
            }


        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Por quantos meses você deixará\n aplicado o seu investimento?",
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = inputMonths,
            onValueChange = { inputMonths = it },
            label = {
                Text(
                    text = "Informe o número de meses"
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                totalAmount = calculateFinalAmount(amount, months)
            }
        ) {
            Text(
                text = "Calcular o rendimento total"
            )
        }
        Text(
            text = totalAmount
        )

    }
}

fun calculateFinalAmount(amount: Double?, months: Double?): String {
    if (amount == null || months == null) {
        return "Inválido"
    }

    val interest = (1.0 + 0.15).pow(months)
    val totalAmount = amount*interest

    val textTotalAmount = NumberFormat.getNumberInstance().format(totalAmount)

    return "O valor total do seu investimento será de: $textTotalAmount"

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompoundInterestCalcTheme {
        MainView()
    }
}