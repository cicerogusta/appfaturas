package com.example.faturas_app.ui.activities.login

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.faturas_app.contract.Contract
import com.example.faturas_app.databinding.ActivityLoginBinding
import com.example.faturas_app.presenter.Presenter
import com.example.faturas_app.ui.activities.home.HomeActivity
import android.graphics.Typeface
import android.view.View


class LoginActivity : AppCompatActivity(), Contract.View.LoginView {

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

            if (verificaCampos()) {
                val presenter = Presenter(loginView = this, context = this)

                presenter.login()
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

    override fun getComponentLoginBinding(): ActivityLoginBinding {
        return binding
    }

    override fun startNewActivity() {
        startActivity(Intent(this, HomeActivity::class.java))

    }

    override fun setupTextViewLogo() {
        val type = Typeface.createFromAsset(assets, "OrelegaOne.ttf")
        binding.textView.typeface = type
        binding.textView.textSize = 30f
        binding.textView.gravity = 2
    }

    override fun createAlertDialog(titulo: String, mensagem: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(titulo)
        dialog.setMessage(mensagem)
        dialog.setPositiveButton("OK", null)
        dialog.setCancelable(true)
        dialog.create().show()

    }

    override fun verificaCampos(): Boolean {
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