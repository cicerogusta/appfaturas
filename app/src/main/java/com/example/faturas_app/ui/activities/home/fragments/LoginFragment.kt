package com.example.faturas_app.ui.activities.home.fragments

import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.faturas_app.R
import com.example.faturas_app.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextViewLogo()
        binding.buttonLogin.setOnClickListener { if (verificaCampos()) Navigation.findNavController(binding.root).navigate(
            R.id.action_nav_login_to_nav_home) }
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
        if (binding.editEmail.text.toString().trim() == "" ) {
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