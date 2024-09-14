package com.example.testcasestudy.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.testcasestudy.presentation.activities.compose_widets.CurrencyScreen
import com.example.testcasestudy.presentation.activities.ui.theme.TestCaseStudyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            TestCaseStudyTheme {
                CurrencyScreen()
            }
        }
    }
}