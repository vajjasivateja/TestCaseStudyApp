package com.example.testcasestudy.presentation.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testcasestudy.R
import com.example.testcasestudy.databinding.ActivityMainBinding
import com.example.testcasestudy.databinding.ActivitySelectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNormalXMLView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnJetpackView.setOnClickListener {
            val intent = Intent(this, MainComposeActivity::class.java)
            startActivity(intent)
        }
    }
}