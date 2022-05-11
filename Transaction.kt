package com.example.project

import com.example.project.Etiquette
import org.w3c.dom.Text
import java.util.*

class Transaction {
    private var text: String? = null
    private var list: ArrayList<Etiquette>? = null
    private var montant = 0
    private lateinit var date: Date

    constructor(text: String, montant: Int, vararg tags: Etiquette) {
        if (montant < 0) {
            println("Montant peut pas etre negatif")
            return
        }
        Objects.requireNonNull(tags)
        for (t in tags) {
            list!!.add(t)
        }
        date = Date()
        this.text = text
    }

    constructor(old: Transaction) {
        montant = old.montant
        list = old.list
        date = Date()
        text = old.text
    }

    fun editMontant(newm: Int) {
        montant = newm
    }

    fun editText(newt: String) {
        text = newt
    }

    fun removeTag(t: Etiquette) {
        list!!.remove(t)
    }

    fun addTag(t: Etiquette) {
        list!!.add(t)
    }
}