package com.moneysync.di

import android.content.Context
import androidx.room.Room
import com.moneysync.data.local.dao.TransactionDao
import com.moneysync.data.local.database.MoneySyncDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MoneySyncDatabase =
        Room.databaseBuilder(
            context,
            MoneySyncDatabase::class.java,
            "moneysync.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTransactionDao(database: MoneySyncDatabase): TransactionDao = database.transactionDao()
}
