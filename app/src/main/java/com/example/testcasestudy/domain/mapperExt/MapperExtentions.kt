package com.example.testcasestudy.domain.mapperExt

import com.example.testcasestudy.data.entities.CurrencyInfoEntity
import com.example.testcasestudy.domain.models.CurrencyInfo

fun CurrencyInfoEntity.toDomain(): CurrencyInfo = CurrencyInfo(id, name, symbol, code)
fun CurrencyInfo.toEntity(): CurrencyInfoEntity = CurrencyInfoEntity(id, name, symbol, code)
