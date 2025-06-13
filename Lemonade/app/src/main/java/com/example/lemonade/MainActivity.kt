package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeMakeSteps()
            }
        }
    }
}


@Preview( showSystemUi = true )
@Composable
fun LemonadeApp () {
    LemonadeMakeSteps(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Composable
fun LemonadeMakeSteps(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box (
                modifier = Modifier
                    .background(color = Color.Yellow)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Lemonade Maker",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(top = 50.dp, bottom = 10.dp)
                        .align(Alignment.Center)
                )
            }

            ActivityFrame(currentStep) {
                step -> currentStep = step
            }
        }
    }
}

@Composable
fun ActivityFrame(
    currentStep: Int,
    onStepChange: (Int) -> Unit
) {
    val stepContent = listOf (
        R.drawable.lemon_tree to R.string.lemon_select,
        R.drawable.lemon_squeeze to R.string.squeeze_lemon,
        R.drawable.lemon_drink to R.string.drink_lemonade,
        R.drawable.lemon_restart to R.string.start_again
    )

    val (imageResource, textResource) = stepContent[currentStep - 1]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = textResource),
            modifier = Modifier
                .size(280.dp)
                .background(
                    color = colorResource(id = R.color.light_green),
                    shape = RoundedCornerShape(40.dp)
                )
                .border(
                    width = 3.dp,
                    color = colorResource(id = R.color.dark_green),
                    shape = RoundedCornerShape(40.dp),
                )
                .clickable { currentStep % 4 + 1 }
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = textResource),
            fontSize = 18.sp,
            color = Color.Gray
        )
    }
}

