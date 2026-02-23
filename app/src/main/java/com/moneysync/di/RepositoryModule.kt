package com.moneysync.di

import com.moneysync.data.repository.TransactionRepositoryImpl
import com.moneysync.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTransactionRepository(
        repositoryImpl: TransactionRepositoryImpl
    ): TransactionRepository
}
