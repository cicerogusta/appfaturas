package com.example.faturas_app.ui.activities.home.fragments

import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.faturas_app.R
import com.example.faturas_app.databinding.FragmentLoginBinding
import com.example.faturas_app.model.JwtResponse
import com.example.faturas_app.model.LoginRequest
import com.example.faturas_app.ui.status.Status
import com.example.faturas_app.viewmodel.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextViewLogo()
        binding.buttonLogin.setOnClickListener {
            login()
        }

    }

    private fun login() {
        if (verificaCampos()) {
            viewModel.getUserToken(LoginRequest(binding.editEmail.text.toString(), binding.editSenha.text.toString())).observe(requireActivity(), Observer {
                when (it.status) {
                    Status.LOADING -> {
                        // Mostrar um loader
                    }
                    Status.SUCCESS -> {
                        // Mostrar sucesso
                    }
                    Status.ERROR -> {
                        // Mostrar mensagem de erro
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }

    private fun setupTextViewLogo() {
        val type = Typeface.createFromAsset(activity?.assets, "OrelegaOne.ttf")
        binding.textView.typeface = type
        binding.textView.textSize = 30f
        binding.textView.gravity = 2
    }

    fun createAlertDialog(titulo: String, mensagem: String) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(titulo)
        dialog.setMessage(mensagem)
        dialog.setPositiveButton("OK", null)
        dialog.setCancelable(true)
        dialog.create().show()

    }

    private fun verificaCampos(): Boolean {
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




