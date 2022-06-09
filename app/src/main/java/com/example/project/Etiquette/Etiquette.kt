package com.example.project.Etiquette

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName="etiquettes")
data class Etiquette (
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name="idEtiquette") var id:Int,
    @ColumnInfo(name="text") var tag: String,
){

}