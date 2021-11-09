package com.example.animequotesv2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotesv2.R
import com.example.animequotesv2.adapter.FraseAdapter
import com.example.animequotesv2.databinding.FragmentDiezFrasesPersonajeBinding
import com.example.animequotesv2.viewmodel.FraseViewModel
import java.lang.Exception

class DiezFrasesPersonajeFragment : Fragment() {

    private var _binding: FragmentDiezFrasesPersonajeBinding? = null
    private val binding get() = _binding!!
    private lateinit var  vmodel:FraseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiezFrasesPersonajeBinding.inflate(inflater, container, false)

        binding.progressBarr.visibility = View.VISIBLE
        binding.rvFrasesPersonaje.visibility = View.GONE

        vmodel = ViewModelProvider(this).get(FraseViewModel::class.java)
        val adapter = FraseAdapter()
        with(binding)
        {
            rvFrasesPersonaje.layoutManager = LinearLayoutManager(context)

            rvFrasesPersonaje.adapter = adapter
            vmodel.traerFrasesPorPersonaje("Goku")

            svBuscaPersonaje.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query!!.isNotEmpty())
                    {
                        Log.v("buscarPersonaje", "La query no esta vacia")
                        vmodel.traerFrasesPorPersonaje(query)
                    }else{
                        Toast.makeText(context, "Debes completar el campo de busqueda antes de realizarla", Toast.LENGTH_SHORT).show()
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })

        }

        vmodel.frases.observe(viewLifecycleOwner, Observer {

            try{
                //Toast.makeText(context, "muestra frases", Toast.LENGTH_SHORT).show()
                adapter.setFrases(it)
            }catch (ex: Exception){
                Log.e("ErrorEnPersonaje", ex.message.toString())
                Toast.makeText(context,"Personaje mal Ingresado", Toast.LENGTH_SHORT).show()
            }
            binding.progressBarr.visibility = View.GONE
            binding.rvFrasesPersonaje.visibility = View.VISIBLE
        })


        return binding.root
    }

}