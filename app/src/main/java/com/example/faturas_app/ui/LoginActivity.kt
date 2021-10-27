package com.example.faturas_app.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.faturas_app.databinding.ActivityLoginBinding
import com.example.faturas_app.model.Login
import com.example.faturas_app.model.Usuario
import com.example.faturas_app.service.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {


    lateinit var binding: ActivityLoginBinding
    var email = ""
    var senha = ""


    var builder = Retrofit.Builder().baseUrl("http://172.17.0.1:8080/")
        .addConverterFactory(GsonConverterFactory.create())

    var retrofit = builder.build()

    var usuarioService = retrofit.create(UsuarioService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.buttonLogin.setOnClickListener {
            email = binding.editEmail.text.toString()
            senha = binding.editSenha.text.toString()
            geraTokenAuth()



        }


    }

    override fun onStart() {
        super.onStart()
        val sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val email = sh.getString("email", null)
        val senha = sh.getString("senha", null)
        if (email != null && senha != null) {
            binding.editEmail.setText(email)
            binding.editSenha.setText(senha)
        }


    }

    fun geraTokenAuth() {

        val login = Login(
            email = binding.editEmail.text.toString(),
            senha = binding.editSenha.text.toString(),

            )
        val call = usuarioService.gerarToken(login)




        call?.enqueue(object : Callback<Usuario?> {
            override fun onResponse(call: Call<Usuario?>, response: Response<Usuario?>) {


                if (response.isSuccessful) {
                    val token = response.body()?.token.toString()
                    val idUsuario = response.body()?.id
                    val sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
                    sh.edit().putString("token", token).apply()
                    if (idUsuario != null) {
                        sh.edit().putLong("id", idUsuario).apply()
                    }
                    getUsuarioApi(token, 1L)


                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Não foi possível acessar a API",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Usuario?>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "login incorreto", Toast.LENGTH_SHORT).show()

            }

        })

    }


    fun getUsuarioApi(token: String, id: Long) {
        val call: Call<Usuario> = usuarioService.getClienteById("Bearer $token", id)
        call.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful && response.body() != null) {
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))

                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
            }
        })


    }


}