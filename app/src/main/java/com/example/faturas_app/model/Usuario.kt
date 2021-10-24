package com.example.faturas_app.model

data class Usuario(
    var id: Long? = null,
    var nome: String,
    var email: String,
    var token: String? = null,
    var cpf: String,
    var diaVencimento: Int,
    var diaCorte: Int

) {

}


