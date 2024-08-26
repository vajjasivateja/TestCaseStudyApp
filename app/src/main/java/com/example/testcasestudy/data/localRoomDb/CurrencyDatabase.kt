package com.example.testcasestudy.data.localRoomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testcasestudy.data.entities.CurrencyInfoEntity

@Database(entities = [CurrencyInfoEntity::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyInfoDao(): CurrencyInfoDao
}
