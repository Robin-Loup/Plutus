package com.example.project
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.*
import kotlin.collections.ArrayList

class Transaction {
    private var text: String = ""
    private var list: ArrayList<Etiquette.Tag>? = null
    private var montant = 0
    private lateinit var date: LocalDateTime


    constructor(text: String, montant: Int, vararg tags: Etiquette.Tag) {
        if (montant < 0) {
            println("Montant peut pas etre negatif")
            return
        }
        Objects.requireNonNull(tags)
        list= ArrayList()
        for (t in tags) {
            list!!.add(t)
        }
        date = now()
        this.text = text
    }

    constructor(old: Transaction) {
        montant = old.montant
        list = old.list
        date = now()
        text = old.text
    }

    fun editMontant(newm: Int) {
        montant = newm
    }

    fun editText(newt: String) {
        text = newt
    }

    fun removeTag(t: Etiquette.Tag) {
        list!!.remove(t)
    }

    fun addTag(t: Etiquette.Tag) {
        list!!.add(t)
    }

    override fun toString(): String {
        return "$text, $montant €, for : $list the $date)"
    }


}

