package com.example.project

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_table WHERE idTransaction = :id")
    fun getTr(id : Int): Transaction

    @Query("SELECT * FROM transaction_table WHERE carnet = :idCarnet")
    fun getEveryTrFrom(idCarnet : Int): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTr(transaction: Transaction)

    @Query("DELETE FROM transaction_table WHERE idTransaction= :id")
    suspend fun deleteTr(id :Int)

    @Update(entity = Transaction::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateTr(transaction:Transaction)
}