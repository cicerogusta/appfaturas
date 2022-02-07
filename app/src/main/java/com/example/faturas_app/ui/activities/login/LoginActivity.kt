package com.example.faturas_app.ui.activities.login

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.faturas_app.databinding.ActivityLoginBinding
import com.example.faturas_app.model.LoginRequest
import com.example.faturas_app.network.api.RetrofitService
import com.example.faturas_app.ui.activities.home.HomeActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var email = ""
    private var senha = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupTextViewLogo()


        binding.buttonLogin.setOnClickListener {
            email = binding.editEmail.text.toString()
            senha = binding.editSenha.text.toString()

            val loginRequest = LoginRequest(email, senha)
            if (verificaCampos()) {
                startActivity(Intent(this, HomeActivity::class.java))


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


     fun setupTextViewLogo() {
        val type = Typeface.createFromAsset(assets, "OrelegaOne.ttf")
        binding.textView.typeface = type
        binding.textView.textSize = 30f
        binding.textView.gravity = 2
    }

     fun createAlertDialog(titulo: String, mensagem: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(titulo)
        dialog.setMessage(mensagem)
        dialog.setPositiveButton("OK", null)
        dialog.setCancelable(true)
        dialog.create().show()

    }

     fun verificaCampos(): Boolean {
        if (binding.editEmail.text.toString().trim() == "") {
            binding.editEmail.error = "Campo email está vazio!"
            return false
        }
        if (binding.editSenha.text.toString().trim() == "") {
            binding.editSenha.error = "Campo senha está vazio!"
            return false

        }
        return true
    }


}