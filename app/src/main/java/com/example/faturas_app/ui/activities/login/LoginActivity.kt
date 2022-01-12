package com.example.faturas_app.ui.activities.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.faturas_app.contract.HomeContract
import com.example.faturas_app.contract.LoginContract
import com.example.faturas_app.databinding.ActivityLoginBinding
import com.example.faturas_app.presenter.Presenter
import com.example.faturas_app.ui.activities.home.HomeActivity


class LoginActivity : AppCompatActivity(), HomeContract.View.LoginView {

    private lateinit var binding: ActivityLoginBinding

    var email = ""
    var senha = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonLogin.setOnClickListener {
            email = binding.editEmail.text.toString()
            senha = binding.editSenha.text.toString()
            val presenter = Presenter(loginView = this, context = this)
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

    override fun getComponentLoginBinding(): ActivityLoginBinding {
        return binding
    }

    override fun startNewActivity() {
        startActivity(Intent(this, HomeActivity::class.java))

    }


}