package com.example.testcasestudy.presentation.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.testcasestudy.R
import com.example.testcasestudy.databinding.ActivityMainBinding
import com.example.testcasestudy.domain.models.CurrencyInfo
import com.example.testcasestudy.presentation.fragments.CurrencyFragment
import com.example.testcasestudy.presentation.viewModels.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CurrencyFragment())
                .commitNow()
        }

        val currencyListA = listOf(
            CurrencyInfo(id = "BTC", name = "Bitcoin", symbol = "BTC", code = null),
            CurrencyInfo(id = "ETH", name = "Ethereum", symbol = "ETH", code = null),
            CurrencyInfo(id = "XRP", name = "XRP", symbol = "XRP", code = null),
            CurrencyInfo(id = "BCH", name = "Bitcoin Cash", symbol = "BCH", code = null),
            CurrencyInfo(id = "LTC", name = "Litecoin", symbol = "LTC", code = null),
            CurrencyInfo(id = "EOS", name = "EOS", symbol = "EOS", code = null),
            CurrencyInfo(id = "BNB", name = "Binance Coin", symbol = "BNB", code = null),
            CurrencyInfo(id = "LINK", name = "Chainlink", symbol = "LINK", code = null),
            CurrencyInfo(id = "NEO", name = "NEO", symbol = "NEO", code = null),
            CurrencyInfo(id = "ETC", name = "Ethereum Classic", symbol = "ETC", code = null),
            CurrencyInfo(id = "ONT", name = "Ontology", symbol = "ONT", code = null),
            CurrencyInfo(id = "CRO", name = "Crypto.com Chain", symbol = "CRO", code = null),
            CurrencyInfo(id = "CUC", name = "Cucumber", symbol = "CUC", code = null),
            CurrencyInfo(id = "USDC", name = "USD Coin", symbol = "USDC", code = null)
        )

        // Define Fiat Currency List (List B)
        val currencyListB = listOf(
            CurrencyInfo(id = "SGD", name = "Singapore Dollar", symbol = "$", code = "SGD"),
            CurrencyInfo(id = "EUR", name = "Euro", symbol = "€", code = "EUR"),
            CurrencyInfo(id = "GBP", name = "British Pound", symbol = "£", code = "GBP"),
            CurrencyInfo(id = "HKD", name = "Hong Kong Dollar", symbol = "$", code = "HKD"),
            CurrencyInfo(id = "JPY", name = "Japanese Yen", symbol = "¥", code = "JPY"),
            CurrencyInfo(id = "AUD", name = "Australian Dollar", symbol = "$", code = "AUD"),
            CurrencyInfo(id = "USD", name = "United States Dollar", symbol = "$", code = "USD")
        )


        binding.btnClear.setOnClickListener {
            viewModel.clearCurrencies()
        }

        binding.btnInsert.setOnClickListener {
            Log.d("data", (currencyListA + currencyListB).toString())
            viewModel.insertCurrencies(currencyListA + currencyListB)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CurrencyFragment())
                .commitNow()
        }

        binding.btnCrypto.setOnClickListener {
            viewModel.insertCurrencies(currencyListA)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CurrencyFragment())
                .commitNow()
        }

        binding.btnFiat.setOnClickListener {
            viewModel.insertCurrencies(currencyListB)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CurrencyFragment())
                .commitNow()
        }

        binding.btnShowPurchasable.setOnClickListener {
            // Logic to show purchasable items (modify based on actual requirement)
            // Assuming purchasable items have some identifier, you'd filter them here.
            val purchasableCurrencies = currencyListA.filter { it.id != "CUC" } + currencyListB
            viewModel.insertCurrencies(purchasableCurrencies)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CurrencyFragment())
                .commitNow()
        }

    }
}