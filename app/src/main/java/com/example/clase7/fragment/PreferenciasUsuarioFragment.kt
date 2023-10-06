package com.example.clase7.fragment

import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import com.example.clase7.R
import com.example.clase7.databinding.FragmentPreferenciasUsuarioBinding
import java.util.Locale

class PreferenciasUsuarioFragment : Fragment() {

    private lateinit var binding: FragmentPreferenciasUsuarioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreferenciasUsuarioBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isModoOscuro()
        definirIdioma()
    }

    private  fun isModoOscuro(){
        binding.swOscuro.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)


            }else{
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        }
    }

    private fun definirIdioma() {
        binding.swCambiarIdioma.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                cambiarIdioma("en")
            }else{
                cambiarIdioma("es")
            }
        }
    }

    private fun cambiarIdioma(idioma: String) {
        val configuracion = Configuration(resources.configuration)
        configuracion.setLocale(Locale(idioma))//todo:Esto indica que quieres que los recursos se carguen en el idioma especificado.
        val contexto = ContextWrapper(requireContext().createConfigurationContext(configuracion))//todo:Este nuevo contexto tiene la configuración actualizada con el nuevo idioma

        binding.tvPreferencias.text = contexto.getString(R.string.preferencias_de_usuario)
        binding.tvHello.text = contexto.getString(R.string.titulo)
        binding.tvDescripcion.text = contexto.getString(R.string.descripcion)
    }

}