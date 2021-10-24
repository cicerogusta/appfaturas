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
    var id = 0



    var builder = Retrofit.Builder().baseUrl("http://10.0.0.113:8080/")
        .addConverterFactory(GsonConverterFactory.create())

    var retrofit = builder.build()

    var usuarioService = retrofit.create(UsuarioService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val token = sh.getString("token", null)

        binding.buttonLogin.setOnClickListener {
            email = binding.editEmail.text.toString()
            senha = binding.editSenha.text.toString()
            if (token != null) {
                getUsuarioApi(token, 1L)
            }

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