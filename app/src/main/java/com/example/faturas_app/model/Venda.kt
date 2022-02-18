package com.example.faturas_app.model

import androidx.core.app.NavUtils
import java.text.SimpleDateFormat
import java.util.*

data class Venda(
    var valor: String,
    var date: String,
    var creditCardNumber: String,
    var expirationDate: String,
    var disponivel: String

) {


}
