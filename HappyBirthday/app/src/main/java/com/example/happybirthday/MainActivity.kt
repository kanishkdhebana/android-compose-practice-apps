package com.example.happybirthday

import android.os.Bundle
import android.text.style.LineHeightSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme
import com.example.happybirthday.Greeting as Greeting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(name = "Android", from = "Kanishk")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(10.dp)
    ) {
        Text(
            text = "Happy Birthday $name!!",
            fontSize = 80.sp,
            lineHeight = 100.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
        )

        Text(
            text = "From $from",
            fontSize = 30.sp,
            modifier = modifier
                .padding(20.dp)
                .align(alignment = Alignment.End)
        )
    }

}

@Preview(showBackground = true,
    showSystemUi = true,
    name = "My Preview")

@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        Greeting(name = "Android", from = "Kanishk")
    }
}