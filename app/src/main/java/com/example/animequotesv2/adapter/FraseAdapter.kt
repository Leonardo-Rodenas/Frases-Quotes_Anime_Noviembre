package com.example.animequotesv2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animequotesv2.R
import com.example.animequotesv2.databinding.ItemRecyclerviewBinding
import com.example.animequotesv2.model.Frase

class FraseAdapter : RecyclerView.Adapter<FraseAdapter.CustomViewHolder>() {

    private var lista: ArrayList<Frase> = ArrayList()

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val binding =ItemRecyclerviewBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.apply {

            tvAnime.text = lista[position].anime
            tvPersonaje.text = lista[position].character
            tvFrase.text = lista[position].quote

        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setFrases(frases: List<Frase>)
    {
        lista = frases as ArrayList<Frase>
        notifyDataSetChanged()
    }
}