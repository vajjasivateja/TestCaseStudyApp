package com.example.testcasestudy.di

import android.app.Application
import androidx.room.Room
import com.example.testcasestudy.data.datasource.LocalDataSource
import com.example.testcasestudy.data.datasource.LocalDataSourceImpl
import com.example.testcasestudy.data.localRoomDb.CurrencyDatabase
import com.example.testcasestudy.data.localRoomDb.CurrencyInfoDao
import com.example.testcasestudy.data.repositories.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(app: Application): CurrencyDatabase {
        return Room.databaseBuilder(app, CurrencyDatabase::class.java, "currency_db").build()
    }

    @Provides
    fun provideCurrencyInfoDao(db: CurrencyDatabase): CurrencyInfoDao {
        return db.currencyInfoDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(currencyDao: CurrencyInfoDao): LocalDataSource {
        return LocalDataSourceImpl(currencyDao)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        localDataSource: LocalDataSource,
    ): CurrencyRepository {
        return CurrencyRepository(localDataSource)
    }

}

