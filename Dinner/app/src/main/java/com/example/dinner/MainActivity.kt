package com.example.dinner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.example.dinner.ui.DinnerApp
import com.example.dinner.ui.theme.DinnerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DinnerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DinnerApp()
                }
            }
        }
    }
}
