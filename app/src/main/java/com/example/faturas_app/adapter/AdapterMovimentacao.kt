package com.example.faturas_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.faturas_app.R
import com.example.faturas_app.databinding.ItemCompraBinding
import com.example.faturas_app.model.apiModel.Venda
import com.example.faturas_app.util.IDateUtil
import java.text.SimpleDateFormat

class AdapterMovimentacao(
    private var listVendas: ArrayList<Venda>
) :
    RecyclerView.Adapter<AdapterMovimentacao.MyViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_compra, parent, false)
        return MyViewHolder(itemLista)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val venda = listVendas[position]
        holder.binding.venda = venda

    }

    override fun getItemCount(): Int {
        return listVendas.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding: ItemCompraBinding by lazy { ItemCompraBinding.bind(itemView) }
    }

}
