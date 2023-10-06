package com.example.clase7

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.clase7.databinding.ItemSuperHeroeBinding

class RecyclerViewHolder(binding: ItemSuperHeroeBinding): RecyclerView.ViewHolder(binding.root) {
    val bindingItem = binding

    fun setItemHeroe(superHeroe: SuperHeroe){
        bindingItem.ivSuperheroe.setImageResource(superHeroe.imagenId)
        bindingItem.tvNombre.text = superHeroe.nombre
        bindingItem.tvPoder.text = superHeroe.poder

        bindingItem.cvHeroes.setOnClickListener {
            Toast.makeText(it.context,"${superHeroe.nombre}", Toast.LENGTH_SHORT).show()
        }
    }
}