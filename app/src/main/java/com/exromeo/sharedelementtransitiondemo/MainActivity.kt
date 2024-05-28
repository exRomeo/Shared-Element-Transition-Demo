package com.exromeo.sharedelementtransitiondemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.exromeo.sharedelementtransitiondemo.common.presentation.navigation.MainNavigation
import com.exromeo.sharedelementtransitiondemo.ui.theme.SharedElementTransitionDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedElementTransitionDemoTheme {
                Surface {
                    MainNavigation(Modifier.fillMaxSize())
                }
            }
        }
    }
}


