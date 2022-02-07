package com.example.faturas_app.model

import androidx.core.app.NavUtils
import java.text.SimpleDateFormat
import java.util.*

data class Venda(
    var valor: String? = null,
    var date: String? = null,
    var creditCardNumber: String? = null,
    var expirationDate: String? = null,
    var disponivel: String? = null

) {
    fun formataDataVenda(): String {

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val output = SimpleDateFormat("dd/MM", Locale.US)
        val d = sdf.parse(date)
        return output.format(d as Date)


    }

    fun formataDataVendaEspecifica(date: String?): String {

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val output = SimpleDateFormat("dd/MM", Locale.US)
        val d = sdf.parse(date)
        return output.format(d as Date)


    }
}
