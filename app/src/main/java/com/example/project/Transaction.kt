package com.example.project

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "transaction_table")
data class Transaction(
    @ColumnInfo(name = "carnet") val carnet: Int,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "montant") var montant: Int,
    var tags: ArrayList<Etiquette.Tag>
) {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idTransaction")
    var id: Int = 0

    @ColumnInfo(name = "date")
    private var date: LocalDateTime = now()

    constructor(old: Transaction) : this(old.carnet, old.text, old.montant, old.tags)

    fun date(): LocalDateTime {
        return date
    }

    override fun toString(): String {
        return "$text, $montant €, for : $tags the $date)"
    }
}


