package com.example.project
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

@Entity(tableName="transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name="idTransaction") var id:Int,
    @ColumnInfo(name="carnet") val carnet: Int,
    @ColumnInfo(name="text") var text: String,
    @ColumnInfo(name="montant") var montant: Int,
    @ColumnInfo(name="date") var date: String = now().format(DateTimeFormatter.ofPattern("dd-MM-yy"))){

//,
//    var tags: ArrayList<Etiquette.Tag>)

    override fun toString(): String {
        return "from $carnet -> $text, $montant €, the $date)"
    }
}

