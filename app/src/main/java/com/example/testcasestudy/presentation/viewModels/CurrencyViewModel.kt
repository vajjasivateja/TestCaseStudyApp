package com.example.testcasestudy.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcasestudy.data.repositories.CurrencyRepository
import com.example.testcasestudy.domain.mapperExt.toDomain
import com.example.testcasestudy.domain.mapperExt.toEntity
import com.example.testcasestudy.domain.models.CurrencyInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _currencies = MutableStateFlow<List<CurrencyInfo>>(emptyList())
    val currencies: StateFlow<List<CurrencyInfo>> = _currencies

    init {
        loadCurrencies()
    }

    private fun loadCurrencies() {
        viewModelScope.launch {
            repository.getAllCurrencies()
                .map { entities -> entities.map { it.toDomain() } }
                .collect { _currencies.value = it }

        }
    }

    fun insertCurrencies(currencies: List<CurrencyInfo>) {
//        Log.d("data currencies", (currencies).toString())
        viewModelScope.launch {
            repository.clearCurrencies()
            repository.insertCurrencies(currencies.map {
//                Log.d("data[${it.id}]", (it).toString())
                it.toEntity()
            })
            loadCurrencies()
        }
    }

    fun clearCurrencies() {
        viewModelScope.launch {
            repository.clearCurrencies()
        }
    }

    fun searchCurrencies(query: String) {
        viewModelScope.launch {
            repository.searchCurrencies(query)
                .map { entities -> entities.map { it.toDomain() } }
                .collect { _currencies.value = it }
        }
    }
}
