package com.example.heyjomui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.heyjomui.ui.theme.HeyjomUITheme

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
                    Column {
                        Header()
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        )
                        {
                            RegisteredEvents()
                            ScrollableHorizontalCardViews()
                            VirtualRuns()
                            VerticalCardViewsList()
                        }
                    }
                }
            }
        }
    }
}



