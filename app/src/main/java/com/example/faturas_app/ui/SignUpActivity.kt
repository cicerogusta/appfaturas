package com.example.faturas_app.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.faturas_app.R
import com.example.faturas_app.databinding.ActivitySignUpBinding
import com.example.faturas_app.model.Login
import com.example.faturas_app.model.Usuario
import com.example.faturas_app.service.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : AppCompatActivity() {

    var binding = ActivitySignUpBinding.inflate(layoutInflater)

    var builder = Retrofit.Builder().baseUrl("http://10.0.0.113:8080/")
        .addConverterFactory(GsonConverterFactory.create())

    var retrofit = builder.build()

    var usuarioService = retrofit.create(UsuarioService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonCadastrar.setOnClickListener {
            geraTokenAuth()

        }
    }

    fun geraTokenAuth() {

        val login = Login(
            email = binding.editEmail.text.toString(),
            senha = binding.editSenhaRegistro.text.toString(),

        )
        val call = usuarioService.gerarToken(login)




        call?.enqueue(object : Callback<Usuario?> {
            override fun onResponse(call: Call<Usuario?>, response: Response<Usuario?>) {
                val token = response.body()?.token

                if (response.isSuccessful && token != null) {
                    val sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
                    sh.edit().putString("token", token).apply()


                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Não foi possível acessar a API",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Usuario?>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "login incorreto", Toast.LENGTH_SHORT).show()

            }

        })


    }

    fun cadastraUsuario() {

        val call = usuarioService.setCliente()
    }
}