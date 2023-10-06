package com.example.clase7

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clase7.databinding.ItemSuperHeroeBinding

class RecyclerAdapter(private val listaHeroes:MutableList<SuperHeroe>):
    RecyclerView.Adapter<RecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemSuperHeroeBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listaHeroes.size
    }

    override fun onBindViewHolder(recyclerViewHolder:RecyclerViewHolder, position: Int) {
        val heroe = listaHeroes[position]
        recyclerViewHolder.setItemHeroe(heroe)
    }
}