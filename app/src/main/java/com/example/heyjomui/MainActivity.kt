package com.example.heyjomui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heyjomui.ui.theme.HeyjomUITheme
import com.example.heyjomui.ui.theme.inter_bold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeyjomUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Use Column to stack composables vertically
                    Column {
                        // Include the Header composable
                        Header()
                        RegisteredEvents()
                        ScrollableHorizontalCardViews()
                    }
                }
            }
        }
    }
}


@Composable
fun RegisteredEvents() {
    Text(
        text = "Registered Events (3)",
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFD9D9D9))
            .padding(16.dp),
        fontSize = 18.sp,
        color = Color.Black,
        textAlign = TextAlign.Start,
        fontFamily = inter_bold
    )
}


