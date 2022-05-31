package com.example.project
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.*
import kotlin.collections.ArrayList

data class Transaction (val carnet : Int, var text: String, var montant: Int, var tags: ArrayList<Etiquette.Tag>){
    private lateinit var date: LocalDateTime

    constructor(old: Transaction) : this(old.carnet,old.text,old.montant,old.tags)

    fun date() : LocalDateTime{
        return date
    }
    override fun toString(): String {
        return "from $carnet -> $text, $montant €, for : $tags the $date)"
    }
}

