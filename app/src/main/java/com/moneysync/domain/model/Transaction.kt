package com.moneysync.domain.model

import java.time.LocalDate

data class Transaction(
    val id: Long = 0,
    val amount: Double,
    val type: TransactionType,
    val categoryId: Long,
    val note: String,
    val date: LocalDate
)
