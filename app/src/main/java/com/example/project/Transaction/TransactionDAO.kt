package com.example.project.Transaction

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=fr#5
 */
@Dao
interface TransactionDAO {
    @Query("SELECT * FROM transactions WHERE idTransaction = :id")
    fun getTr(id : Int): Transaction

    @Query("SELECT * FROM transactions WHERE carnet = :id")
    fun getEveryTrFrom(id : Int): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions")
    fun getAllTr(): LiveData<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTr(transaction: Transaction)

    @Query("DELETE FROM transactions WHERE idTransaction= :id")
    suspend fun deleteTr(id :Int)

    @Query("DELETE FROM transactions")
    suspend fun deleteAll()

    @Update(entity = Transaction::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateTr(transaction: Transaction)
}