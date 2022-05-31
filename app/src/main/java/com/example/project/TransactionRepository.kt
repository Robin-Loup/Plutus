package com.example.project

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {

    //Help
    val TransactionByCarnet: Flow<List<Transaction>> = transactionDao.getEveryTrFrom()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transaction: Transaction) {
        transactionDao.insertTr(transaction)
    }
}