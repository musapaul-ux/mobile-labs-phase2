package com.ndejje.momocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ndejje.momocal.ui.theme.MoMo_CalculatorTheme
//import androidx.content.res.Configuration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoMoAppTheme { // replaces raw MaterialTheme(..)
 //               our custom theme (part b)
                Surface(modifier = Modifier.fillMaxSize()){
                    Scaffold(
                        topBar = {MoMoTopBar()}
                    ){ innerPadding ->
                        MoMoCalcScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
             }
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
    isError: Boolean = false,
    modifier:Modifier = Modifier
){
    Column(modifier = Modifier) {
        // modifier applied to outer Column
        TextField(
            value = amount,
            onValueChange = onAmountChange,
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
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
fun MoMoCalcScreen(
    modifier:Modifier = Modifier
){
    var amountInput by remember { mutableStateOf("")}

    val numericAmount = amountInput.toDoubleOrNull()

    val isError = amountInput.isNotEmpty() && numericAmount == null


    val fee = when {
        numericAmount == null -> 0.0
        numericAmount < 0.0 -> 0.0
        numericAmount in 0.0..2499999.0 -> numericAmount * 0.03
        else -> numericAmount * 0.015
    }

    val formattedFee = "UGX %,.0f".format(fee)

    Column(
        modifier = Modifier
//            recieves innerpadding from scaffold
            .fillMaxSize()
            // Occupy full screen - centering needs space
            .padding(dimensionResource(R.dimen.screen_padding)),
        verticalArrangement = Arrangement.Center,  // vertically  middle
        horizontalAlignment = Alignment.CenterHorizontally // horizontal center
    ){
       Text(
           text = stringResource(R.string.app_title),
           style = MaterialTheme.typography.headlineMedium,
           textAlign = TextAlign.Center  // Centres text within its own bounding box
       )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))

        HoistedAmountInput(
            amount = amountInput,
            onAmountChange = {amountInput = it},
            isError = isError,
            modifier = Modifier.fillMaxWidth() // input stretches full width
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))

        Text(
            text = stringResource(R.string.fee_label, formattedFee),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoMoTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_title),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.ic_momo_logo),
                contentDescription = "MoMo logo",
                modifier = Modifier
                    .padding(start = dimensionResource(R.dimen.spacing_medium))
                    .height(32.dp)
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(50.dp)),
                contentScale = ContentScale.Fit
            )
        },

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}


@Preview(
    name = "Light Mode",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun previewLight() {
    MoMoAppTheme(darkTheme = false){
        MoMoCalcScreen()
    }
}


@Preview(
    name = "Dark Mode",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun previewDark() {
    MoMoAppTheme(darkTheme = true){
        MoMoCalcScreen()
    }
}



