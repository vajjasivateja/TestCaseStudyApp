package com.example.testcasestudy.data.datasource

import com.example.testcasestudy.data.entities.CurrencyInfoEntity
import com.example.testcasestudy.data.localRoomDb.CurrencyInfoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalDataSource {
    fun getAllCurrencies(): Flow<List<CurrencyInfoEntity>>
    suspend fun insertCurrencies(currencies: List<CurrencyInfoEntity>)
    suspend fun clearCurrencies()
    fun searchCurrencies(namePrefix: String, partialMatch: String, symbolPrefix: String): Flow<List<CurrencyInfoEntity>>
}

class LocalDataSourceImpl @Inject constructor(
    private val currencyDao: CurrencyInfoDao
) : LocalDataSource {

    override fun getAllCurrencies(): Flow<List<CurrencyInfoEntity>> = currencyDao.getAllCurrencies()

    override suspend fun insertCurrencies(currencies: List<CurrencyInfoEntity>) {
        currencyDao.insertAll(currencies)
    }

    override suspend fun clearCurrencies() {
        currencyDao.clearAll()
    }

    override fun searchCurrencies(namePrefix: String, partialMatch: String, symbolPrefix: String): Flow<List<CurrencyInfoEntity>> {
        return currencyDao.searchCurrencies(namePrefix, partialMatch, symbolPrefix)
    }
}