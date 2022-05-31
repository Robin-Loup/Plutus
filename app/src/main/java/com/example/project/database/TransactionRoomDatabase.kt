package com.example.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction
import com.example.project.TransactionDAO

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Transaction::class), version = 1, exportSchema = false)
abstract class TransactionRoomDatabase : RoomDatabase(){

    abstract fun TransactionDao(): TransactionDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TransactionRoomDatabase? = null

        fun getDatabase(context: Context): TransactionRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransactionRoomDatabase::class.java,
                    "transaction_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}