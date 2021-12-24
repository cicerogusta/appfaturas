package com.example.faturas_app.ui.activitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.faturas_app.databinding.ActivityLoginBinding
import com.example.faturas_app.model.apiModel.Login
import com.example.faturas_app.model.apiModel.Token
import com.example.faturas_app.network.retrofit.RetrofitCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    var email = ""
    var senha = ""

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
        val call = RetrofitCall.retrofit().gerarToken(login)

        call.enqueue(object : Callback<Token?> {
            override fun onResponse(call: Call<Token?>, response: Response<Token?>) {

                if (response.isSuccessful) {
                    val token = response.body()?.token
                    val sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
                    sh.edit().putString("token", token).apply()
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))

                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Não foi possível acessar a API",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Token?>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "login incorreto", Toast.LENGTH_SHORT).show()

            }

        })

    }
}