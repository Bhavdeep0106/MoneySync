package com.moneysync.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moneysync.data.local.dao.TransactionDao
import com.moneysync.data.local.entity.CategoryEntity
import com.moneysync.data.local.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoneySyncDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
