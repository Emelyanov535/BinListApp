package com.example.binlistapp

data class Card(val scheme: String,
                val brand: String,
                val type: String,
                val prepaid: String,
                val country: String,
                val bank: String){
    fun output(): String {
        return scheme + "   " + type + "\n" +
                brand + "   " + prepaid + "\n" +
                country + "   " + bank

    }
}
