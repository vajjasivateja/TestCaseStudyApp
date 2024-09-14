package com.example.testcasestudy.presentation.activities.compose_widets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.testcasestudy.R
import com.example.testcasestudy.domain.models.CurrencyInfo
import com.example.testcasestudy.presentation.activities.ui.theme.TestCaseStudyTheme
import com.example.testcasestudy.presentation.viewModels.CurrencyViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CurrencyScreen(viewModel: CurrencyViewModel = hiltViewModel()) {
    val currencyList by viewModel.currencies.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search bar
        SearchView(
            query = searchQuery,
            onQueryChange = {
                searchQuery = it
                scope.launch {
                    delay(300) // debounce delay
                    viewModel.searchCurrencies(searchQuery)
                }
            },
            onSearch = {
                viewModel.searchCurrencies(searchQuery)
            }
        )

        // Spacer to add space between search and list
        Spacer(modifier = Modifier.height(16.dp))

        // List of Currencies
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Take up remaining space
        ) {
            items(currencyList.size, itemContent = { index ->
                CurrencyItem(currencyList[index])
            })
        }

        // Buttons for actions
        ActionButtons(
            viewModel, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
    }
}

@Composable
fun SearchView(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = { onQueryChange(it) },
        placeholder = {
            Text(text = "Search Here")
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_clear),
                        contentDescription = "Clear"
                    )
                }
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "Search Icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .background(color = Color.White)
            .padding(horizontal = 8.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
            }
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray
        )
    )
}

@Composable
fun ActionButtons(viewModel: CurrencyViewModel, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = { viewModel.clearCurrencies() }) {
            Text("Clear")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            viewModel.insertCurrencies(currencyListA + currencyListB)
        }) {
            Text("Insert All")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            // Insert only Crypto (List A)
            viewModel.insertCurrencies(currencyListA)
        }) {
            Text("Insert Crypto")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            // Insert only Fiat (List B)
            viewModel.insertCurrencies(currencyListB)
        }) {
            Text("Insert Fiat")
        }
    }
}

@Composable
fun CurrencyList(currencyList: List<CurrencyInfo>) {
    LazyColumn {
        items(currencyList.size) { index ->
            CurrencyItem(currencyList[index])
        }
    }
}

@Composable
fun CurrencyItem(currency: CurrencyInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = currency.name, style = MaterialTheme.typography.body1)
        Text(text = currency.symbol ?: "", style = MaterialTheme.typography.body2)
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyScreenPreview() {
    TestCaseStudyTheme {
        CurrencyScreen()
    }
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
