package com.radlab.wzorce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.radlab.wzorce.ui.screens.PatternsScreen
import com.radlab.wzorce.ui.theme.WzorceTheme
import com.radlab.wzorce.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val mainViewModel: MainViewModel by viewModels()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WzorceTheme {
                    PatternsScreen(mainViewModel)
            }
        }
    }
}

