package com.ndejje.momocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

//@Composable
//fun BrokenInput(){
//    var amount = "0"
//
//    TextField(
//        value = amount,
//        onValueChange = {amount = it},
//        label = {
//            Text(
//                stringResource(R.string.enter_amount)
//            )
//        }
//    )
//}

//@Composable
//fun InternalStateInput(){
//    var amount  by remember {mutableStateOf("0")}
//    TextField(
//        value = amount,
//        onValueChange = {amount = it},
//        label = {
//            Text(
//                stringResource(R.string.enter_amount)
//            )
//        }
//    )
//}

@Composable
fun HoistedAmountInput(
    amount: String, // Allows state to flow in
    onAmountChange: (String) -> Unit, //events flow out
    isError: Boolean = false
){
    Column {
        TextField(
            value = amount,
            onValueChange = onAmountChange,
            isError = isError,
            label = {
                stringResource(R.string.enter_amount)
            }
        )
        if (isError) {
            Text(
                text = stringResource(R.string.error_numbers_only),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun MoMoCalcScreen(){
    var amountInput by remember { mutableStateOf("")}

    val numericAmount = amountInput.toDoubleOrNull()

    val isError = amountInput.isNotEmpty() && numericAmount == null


    val fee = when {
        numericAmount == null -> 0.0
        numericAmount in 0.0..2499999.0 -> numericAmount * 0.03
        else -> numericAmount * 0.015
    }

    val formattedFee = "UGX %,.0f".format(fee)

    Column(
        modifier = Modifier.padding(16.dp)
    ){
       Text(
           text = stringResource(R.string.app_title),
           style = MaterialTheme.typography.headlineMedium
       )
        Spacer(modifier = Modifier.height(16.dp))

        HoistedAmountInput(
            amount = amountInput,
            onAmountChange = {amountInput = it},
            isError = isError
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.fee_label, formattedFee),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}



@Preview(showBackground = true)
@Composable
fun MoMoCalcPreview() {
    MaterialTheme{
        MoMoCalcScreen()
    }
}




