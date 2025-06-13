package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1F))

        Box (
            modifier = Modifier
                .background(color = Color.White)
                .shadow(elevation = 3.dp, shape = RectangleShape)
                .padding(16.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "sadfsa",
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.Center)
                )
        }

        ArtworkInfo(Title = "sdfsadf", Artist = "sadf")

        Spacer(modifier = Modifier.weight(1F))

        PreviousNextButtons()
    }
}

@Composable
fun ArtworkInfo (
    Title: String,
    Artist: String,
    modifier: Modifier = Modifier

) {
    Column(

    ) {
        Text(
            text = Title,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold ,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp)


        )

        Text(
            text = Artist,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp)
        )
    }
}

@Composable
fun PreviousNextButtons(
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonColor),
                contentColor = Color.White
            ),
            modifier = modifier
                .fillMaxWidth(0.5F)
                .weight(1F)
                .padding(5.dp)
        ) {
            Text(
                text = stringResource(id = R.string.previous),
                fontSize = 18.sp
            )
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonColor),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(0.5F)
                .weight(1F)
                .padding(5.dp)

        ) {
            Text(
                text = stringResource(id = R.string.next),
                fontSize = 18.sp
            )
        }
    }
}


@Preview(
    showSystemUi = true,
    showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}

