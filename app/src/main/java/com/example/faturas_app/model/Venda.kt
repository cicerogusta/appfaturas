package com.example.faturas_app.model

import java.math.BigDecimal

data class Venda(
    var qtdParcelas:Int?,
    var valor:BigDecimal?




){


    fun ValorToString(): String {
        return "Venda(valor=$valor)"
    }

    fun ParcelaToString(): String {
        return "Venda(qtdParcelas=$qtdParcelas)"
    }
}
