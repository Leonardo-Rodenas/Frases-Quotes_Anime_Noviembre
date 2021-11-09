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
import com.example.animequotesv2.databinding.FragmentDiezFrasesAnimeBinding
import com.example.animequotesv2.viewmodel.FraseViewModel
import java.lang.Exception


class DiezFrasesAnimeFragment : Fragment() {

    private var _binding: FragmentDiezFrasesAnimeBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmodel: FraseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiezFrasesAnimeBinding.inflate(inflater, container, false)

        binding.progressBarr.visibility = View.VISIBLE
        binding.rvFrasesAnime.visibility = View.GONE

        vmodel = ViewModelProvider(this).get(FraseViewModel::class.java)

        val adapter = FraseAdapter()
        with(binding)
        {
            rvFrasesAnime.layoutManager = LinearLayoutManager(context)

            rvFrasesAnime.adapter = adapter
            vmodel.traerFrasesPorAnime("Naruto")

            svBuscaAnime.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query!!.isNotEmpty())
                    {
                        Log.v("buscarAnime", "La query no esta vacia")
                        vmodel.traerFrasesPorAnime(query)
                    }else{
                        Log.v("Toastenqueryvacia", "query vacia")
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

            try {
                //Toast.makeText(context, "muestra frases", Toast.LENGTH_SHORT).show()
                adapter.setFrases(it)
            } catch (ex: Exception) {
                Log.e("ErrorEnAnime", ex.message.toString())
                Toast.makeText(context, "Anime mal ingresado", Toast.LENGTH_SHORT).show()
            }
            binding.progressBarr.visibility = View.GONE
            binding.rvFrasesAnime.visibility = View.VISIBLE

        })

        return binding.root
    }
}