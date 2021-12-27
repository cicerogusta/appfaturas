package com.example.faturas_app.ui.activitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.faturas_app.contract.LoginContract
import com.example.faturas_app.databinding.ActivityLoginBinding
import com.example.faturas_app.presenter.LoginPresenter


class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityLoginBinding

    override fun getBinding(): ActivityLoginBinding {
        return binding
    }

    var email = ""
    var senha = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonLogin.setOnClickListener {
            email = binding.editEmail.text.toString()
            senha = binding.editSenha.text.toString()
            val presenter = LoginPresenter(this)
            presenter.login()

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

    override fun startNewActivity() {
        startActivity(Intent(this, HomeActivity::class.java))

    }

    override fun saveToken(token: String) {
        getSharedPreferences("MySharedPref", Context.MODE_PRIVATE).edit().putString("token", token)
            .apply()
    }


}