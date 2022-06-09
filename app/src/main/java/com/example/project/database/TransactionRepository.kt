package com.example.project.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.project.Transaction.Transaction
import com.example.project.Transaction.TransactionDAO

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class TransactionRepository(private val transactionDAO: TransactionDAO) {

    val allTransactions: LiveData<List<Transaction>> = transactionDAO.getAllTr()
    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transaction: Transaction) {
        transactionDAO.insertTr(transaction)
    }
    fun getAll() : LiveData<List<Transaction>> {
        return transactionDAO.getAllTr()
    }
    suspend fun dropAll() {
        transactionDAO.deleteAll()
    }
}