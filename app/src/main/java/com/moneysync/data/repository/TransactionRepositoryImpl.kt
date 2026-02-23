package com.moneysync.data.repository

import com.moneysync.data.local.dao.TransactionDao
import com.moneysync.data.mapper.toDomain
import com.moneysync.data.mapper.toEntity
import com.moneysync.domain.model.DashboardSummary
import com.moneysync.domain.model.Transaction
import com.moneysync.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionRepository {
    override fun getTransactions(): Flow<List<Transaction>> = dao.getTransactions()
        .map { list -> list.map { it.toDomain() } }

    override fun getDashboardSummary(): Flow<DashboardSummary> = combine(
        dao.totalIncome(),
        dao.totalExpense()
    ) { income, expense ->
        DashboardSummary(
            totalIncome = income,
            totalExpense = expense,
            netBalance = income - expense
        )
    }

    override suspend fun addTransaction(transaction: Transaction) {
        dao.upsert(transaction.toEntity())
    }

    override suspend fun deleteTransaction(transactionId: Long) {
        dao.deleteById(transactionId)
    }
}
