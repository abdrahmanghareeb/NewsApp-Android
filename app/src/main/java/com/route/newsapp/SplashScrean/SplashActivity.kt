package com.route.newsapp.SplashScrean

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsapp.R
import com.route.newsapp.Main.MainActivity
import com.route.newsapp.Main.ui.theme.NewsAppTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                SplashScreen()
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this@SplashActivity , MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },2500)
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.FillBounds
            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.heightIn(60.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo", modifier = Modifier.fillMaxSize(.45f)
        )
        Image(
            painter = painterResource(id = R.drawable.signature),
            contentDescription = " signature",
            modifier = Modifier.fillMaxSize(.45f)
        )
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NewsAppTheme {
        SplashScreen()
    }
}