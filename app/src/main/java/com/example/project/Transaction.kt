package com.example.project
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName="transactions")
data class Transaction (
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name="idTransaction") var id:Int,
    @ColumnInfo(name="carnet") val carnet : Int,
    @ColumnInfo(name="text") var text: String,
    @ColumnInfo(name="montant") var montant: Int,
    @ColumnInfo(name="date") var date: LocalDateTime = now(),
    var tags: ArrayList<Etiquette.Tag>){



    override fun toString(): String {
        return "from $carnet -> $text, $montant €, for : $tags the $date)"
    }
}

