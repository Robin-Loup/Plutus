package com.example.project

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=fr#5
 */
@Dao
interface TransactionDAO {
    @Query("SELECT * FROM transactions WHERE idTransaction = :id")
    fun getTr(id : Int): Transaction

    @Query("SELECT * FROM transactions WHERE carnet = :id")
    fun getEveryTrFrom(id : Int): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions")
    fun getAllTr(): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTr(transaction: Transaction)

    @Query("DELETE FROM transactions WHERE idTransaction= :id")
    suspend fun deleteTr(id :Int)

    @Update(entity = Transaction::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateTr(transaction:Transaction)
}