package com.example.faturas_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.faturas_app.databinding.ActivityMainBinding
import com.example.faturas_app.model.Login
import com.example.faturas_app.model.Usuario
import com.example.faturas_app.service.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    var builder = Retrofit.Builder().baseUrl("http://10.0.0.113:8080/")
        .addConverterFactory(GsonConverterFactory.create())

    var retrofit = builder.build()

    var usuarioService = retrofit.create(UsuarioService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGetToken.setOnClickListener { login() }
    }

    lateinit var token: String

    fun login() {
        val login = Login("aluno@email.com", "123456")
        val call = usuarioService.login(login)
        call!!.enqueue(object : Callback<Usuario?> {
            override fun onResponse(call: Call<Usuario?>, response: Response<Usuario?>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, response.body()?.token, Toast.LENGTH_SHORT).show()
                    token = response.body()!!.token



                }
                else {
                    Toast.makeText(this@MainActivity, "login incorreto", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Usuario?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}