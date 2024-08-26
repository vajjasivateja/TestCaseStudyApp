package com.example.testcasestudy.data.localRoomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testcasestudy.data.entities.CurrencyInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<CurrencyInfoEntity>)

    @Query("SELECT * FROM currency_info ORDER BY name ASC")
    fun getAllCurrencies(): Flow<List<CurrencyInfoEntity>>

    @Query("DELETE FROM currency_info")
    suspend fun clearAll()

    @Query(
        """
        SELECT * FROM currency_info
        WHERE 
        name LIKE :namePrefix || '%' OR 
        name LIKE '%' || :partialMatch OR 
        symbol LIKE :symbolPrefix || '%' 
        ORDER BY name ASC
    """
    )
    fun searchCurrencies(
        namePrefix: String,
        partialMatch: String,
        symbolPrefix: String
    ): Flow<List<CurrencyInfoEntity>>

}
