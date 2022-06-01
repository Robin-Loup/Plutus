package com.example.project

import android.app.Application
import com.example.project.database.TransactionRepository
import com.example.project.database.TransactionRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PlutusApp : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())


    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { TransactionRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { TransactionRepository(database.TransactionDao()) }

}