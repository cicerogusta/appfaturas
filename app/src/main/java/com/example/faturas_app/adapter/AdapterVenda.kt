package com.example.faturas_app.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.faturas_app.R
import com.example.faturas_app.databinding.RowParcelasBinding
import com.example.faturas_app.model.Venda

class AdapterVenda(
    private var movimentacoes: MutableList<Venda>,
    var context: Context,
    val assetManager: AssetManager
) :
    RecyclerView.Adapter<AdapterVenda.MyViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLista = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_parcelas, parent, false)
        return MyViewHolder(itemLista)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

//        val textViewFont = Typeface.createFromAsset( assetManager,"Anton-Regular.ttf")
//
//        val movimentacao = movimentacoes[position]
//        holder.binding.movimentacao = movimentacao
//
//
//        holder.binding.value.setTextColor(context.resources.getColor(R.color.colorAccentReceita, null))
//        holder.binding.value.typeface = textViewFont
//
//
//
//        if (movimentacao.tipo == "d") {
//            holder.binding.value.setTextColor(context.resources.getColor(R.color.colorAccent, null))
//            holder.binding.value.text = "-" + movimentacao.valor
//
//
//        }

        holder.binding.deleteBtn.setOnClickListener {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)

            alertDialog.setTitle("Excluir Movimentação da Conta")
            alertDialog.setMessage("Você tem certeza que deseja realmente excluir essa movimentação de sua conta?")
            alertDialog.setCancelable(false)
            alertDialog.setPositiveButton(
                "Confirmar"
            ) { _, _ ->







            }
            alertDialog.setNegativeButton(
                "Cancelar"
            ) { dialog, _ ->

                dialog.dismiss()
            }
            alertDialog.create()?.show()
        }


    }

    override fun getItemCount(): Int {
        return movimentacoes.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding: RowParcelasBinding by lazy { RowParcelasBinding.bind(itemView) }
    }

}
