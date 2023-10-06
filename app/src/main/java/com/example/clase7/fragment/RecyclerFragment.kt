package com.example.clase7.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clase7.R
import com.example.clase7.RecyclerAdapter
import com.example.clase7.SuperHeroe
import com.example.clase7.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler()
    }

    fun recycler(){

        var listaHeores = mutableListOf(
            SuperHeroe("SuperMan","Superman es conocido por su increíble fuerza física. Puede levantar objetos extremadamente pesados, desde automóviles hasta edificios.", R.drawable.superman),
            SuperHeroe("Hulk", "Radica en su capacidad para transformarse de un físico débil y tímido llamado Bruce Banner en un gigante verde y musculoso conocido como Hulk", R.drawable.hulk),
            SuperHeroe("Batman", "En lugar de depender de poderes extraordinarios, el superhéroe Batman confía en su inteligencia, entrenamiento físico y habilidades técnicas para combatir el crimen", R.drawable.batman),
            SuperHeroe("Capitan América","No posee poderes sobrenaturales, ha sido mejorado físicamente a través de un suero de súper soldado", R.drawable.capitan),
            SuperHeroe("Pantera Negra","Fuerza Sobrehumana, Sentidos Agudizados, Agilidad y Velocidad Mejoradas,Maestría en Artes Marciales", R.drawable.pantera),
            SuperHeroe("Spider-Man", "Sentido arácnido, agilidad", R.drawable.spiderman)
        )

        val recycler = binding.recyclerview
        recycler.layoutManager = LinearLayoutManager(context)// todo: lista vertical
        //recycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)// todo:lista horizontal
        //val countColum = 2
        //recycler.layoutManager = GridLayoutManager(context, countColum) //todo:En forma de tabla
        val adapter = RecyclerAdapter(listaHeores)
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}