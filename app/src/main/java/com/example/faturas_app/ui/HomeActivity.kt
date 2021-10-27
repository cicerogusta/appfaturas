package com.example.faturas_app.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.faturas_app.adapter.AdapterVenda
import com.example.faturas_app.databinding.ActivityHomeBinding
import com.example.faturas_app.service.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.faturas_app.model.Usuario
import com.example.faturas_app.model.Venda
import java.util.*


class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding


    var builder = Retrofit.Builder().baseUrl("http://10.0.0.113:8080/")
        .addConverterFactory(GsonConverterFactory.create())

    var retrofit = builder.build()

    var usuarioService = retrofit.create(UsuarioService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val token = sh.getString("token", null)
        val id = sh.getLong("id", 0)

        if (token != null && id > 0) {
//            getUsuarioApi(token, id)
            getVendas(token, id)
        }


    }


    fun getVendas(token: String, id: Long) {
        val call: Call<Venda> = usuarioService.getVendaById("Bearer $token", id)
        call.enqueue(object : Callback<Venda> {
            override fun onResponse(call: Call<Venda>, response: Response<Venda>) {
                val listaVendas = mutableListOf<Venda>()
                if (response.isSuccessful) {
                    response.body()?.let { listaVendas.add(it) }

                }

                binding.recyclerVendas.adapter =
                    AdapterVenda(listaVendas, this@HomeActivity, assets)
            }

            override fun onFailure(call: Call<Venda>, t: Throwable) {
            }

        })
    }
}
