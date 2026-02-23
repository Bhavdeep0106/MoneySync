package com.moneysync.domain.repository

import com.moneysync.domain.model.DashboardSummary
import com.moneysync.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactions(): Flow<List<Transaction>>
    fun getDashboardSummary(): Flow<DashboardSummary>
    suspend fun addTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transactionId: Long)
}
