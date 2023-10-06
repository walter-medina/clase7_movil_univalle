package com.example.clase7.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.clase7.MainActivity
import com.example.clase7.R
import com.example.clase7.databinding.FragmentHomeBinding

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted->
            if(isGranted){
                abrirCamara()
            }else{
                Toast.makeText(context,"Necesitas aceptar los Permisos",Toast.LENGTH_SHORT).show()

            }
        }

    private lateinit var binding: FragmentHomeBinding
    private val idChanel = "idChanel"
    private val nameChanel = "nameChanel"
    private val notificacionId = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler()
        preferenciasUsuario()
        permisos()
        lanzarNotificacion()
    }

    private fun recycler() {
        binding.btnFragmentRecycler.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recyclerFragment)
        }

    }

    private fun preferenciasUsuario() {
        binding.btnPreferencias.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_preferenciasUsuarioFragment)
        }
    }

    private fun permisos() {
        binding.btnPermisos.setOnClickListener {
            solicitudPermisoCamera()
            //abrirCamara()
        }
    }

    private fun abrirCamara() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 0)

    }


    private fun solicitudPermisoCamera(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                //Todo:Cuando ya se ha aceptado el permiso
                ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA
                )== PackageManager.PERMISSION_GRANTED ->{
                    abrirCamara()
                }

                //Todo: Cuando se pide el permiso y se rechaza
                shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA) ->{
                    AlertDialog.Builder(context)
                        .setTitle("Permisos de Cámara")
                        .setMessage("Acepta los permisos")
                        .setPositiveButton("SI"){_,_ ->
                            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                        }
                        .setNegativeButton("No"){ _, _ -> }.show()
                }
                else ->{
                    //todo: cuando se entra a la camara por primera vez
                    requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
            }
        }else{
            abrirCamara()
        }
    }

    private fun lanzarNotificacion() {
        binding.btnNotificaciones.setOnClickListener {
            crearNotificacion()
        }
    }

    private fun crearCanalNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(idChanel, nameChanel, importance).apply {
                lightColor = Color.RED
                enableLights(true)
            }
            val notificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    @SuppressLint("MissingPermission")
    private fun crearNotificacion() {
        crearCanalNotificacion()


        val notification = NotificationCompat.Builder(requireContext(), idChanel)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Título Notificación")
            .setContentText("Esto es una notificación")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        NotificationManagerCompat.from(requireContext())
            .notify(notificacionId, notification.build())
    }
}