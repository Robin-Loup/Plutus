package com.example.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.project.Etiquette
import com.example.project.Transaction
import com.example.project.TransactionDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime.now

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Transaction::class), version = 1, exportSchema = false)
abstract class TransactionRoomDatabase : RoomDatabase(){

    abstract fun TransactionDao(): TransactionDAO

    private class TransactionDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var transactionDAO = database.TransactionDao()

                    // Delete all content here.
                    transactionDAO.deleteAll()

                    // Add sample words.
                    var tr = Transaction(0,0,"test",10, now(),arrayListOf<Etiquette.Tag>(Etiquette.Tag.Food))
                    transactionDAO.insertTr(tr)

                    // TODO: Add your own words!
                }
            }
        }
    }


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TransactionRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): TransactionRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransactionRoomDatabase::class.java,
                    "transaction_database"
                )
                    .addCallback(TransactionDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}