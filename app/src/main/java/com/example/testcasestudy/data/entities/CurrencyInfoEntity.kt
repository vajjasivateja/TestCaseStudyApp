package com.example.testcasestudy.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_info")
data class CurrencyInfoEntity(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val code: String?
)

