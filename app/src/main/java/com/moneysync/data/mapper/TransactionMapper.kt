package com.moneysync.data.mapper

import com.moneysync.data.local.entity.TransactionEntity
import com.moneysync.domain.model.Transaction
import com.moneysync.domain.model.TransactionType
import java.time.LocalDate

fun TransactionEntity.toDomain(): Transaction = Transaction(
    id = id,
    amount = amount,
    type = TransactionType.valueOf(type),
    categoryId = categoryId,
    note = note,
    date = LocalDate.ofEpochDay(dateEpochDay)
)

fun Transaction.toEntity(): TransactionEntity = TransactionEntity(
    id = id,
    amount = amount,
    type = type.name,
    categoryId = categoryId,
    note = note,
    dateEpochDay = date.toEpochDay()
)
