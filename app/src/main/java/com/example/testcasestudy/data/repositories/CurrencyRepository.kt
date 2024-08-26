package com.example.testcasestudy.data.repositories

import com.example.testcasestudy.data.datasource.LocalDataSource
import com.example.testcasestudy.data.entities.CurrencyInfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CurrencyRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
) {
    fun getAllCurrencies(): Flow<List<CurrencyInfoEntity>> {
        return localDataSource.getAllCurrencies()
    }

    suspend fun insertCurrencies(currencies: List<CurrencyInfoEntity>) {
        localDataSource.insertCurrencies(currencies)
    }

    suspend fun clearCurrencies() {
        localDataSource.clearCurrencies()
    }

    fun searchCurrencies(query: String): Flow<List<CurrencyInfoEntity>> {
        val namePrefix = "$query%"
        val partialMatch = "% $query%"
        val symbolPrefix = "$query%"

        return localDataSource.searchCurrencies(namePrefix, partialMatch, symbolPrefix)
    }
}
