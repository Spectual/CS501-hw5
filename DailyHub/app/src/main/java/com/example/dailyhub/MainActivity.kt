package com.example.dailyhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.dailyhub.ui.HubApp
import com.example.dailyhub.ui.theme.DailyHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyHubTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    HubApp()
                }
            }
        }
    }
}
