package com.example.project.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.project.Etiquette.Etiquette
import com.example.project.Transaction.Transaction

data class TransactionToEtiquette(@Embedded val transaction:Transaction,
                                  @Relation(
                                      parentColumn = "idTransaction",
                                      entityColumn = "idEtiquette",
                                      associateBy = Junction(EtiquetteCrossRef::class)
                                  )
                                  var etiquettes:List<Etiquette> = arrayListOf()) {

}


@Entity(primaryKeys = ["idTransaction","idEtiquette"])
data class EtiquetteCrossRef(var transactionId : Int, var etiquetteId : Int)