package com.example.animequotesv2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotesv2.adapter.FraseAdapter
import com.example.animequotesv2.databinding.FragmentFraseRandomBinding
import com.example.animequotesv2.viewmodel.FraseViewModel
import java.lang.Exception

class FraseRandomFragment : Fragment() {

    private var _binding: FragmentFraseRandomBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmodel: FraseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFraseRandomBinding.inflate(inflater, container, false)

        binding.progressBarr.visibility = View.VISIBLE
        binding.rvFrasesRandom.visibility = View.GONE

        vmodel = ViewModelProvider(this).get(FraseViewModel::class.java)

        val adapter = FraseAdapter()
        with(binding)
        {
            rvFrasesRandom.layoutManager = LinearLayoutManager(context)

            rvFrasesRandom.adapter = adapter
            vmodel.traerDiezFrases()
        }

        vmodel.frases.observe(viewLifecycleOwner, Observer {

            try{
                adapter.setFrases(it)
            }catch (ex:Exception){
                Log.e("ErrorEnRandom", ex.message.toString())
                Toast.makeText(context,"Frases cargadas erróneamente", Toast.LENGTH_SHORT).show()
            }
            binding.progressBarr.visibility = View.GONE
            binding.rvFrasesRandom.visibility = View.VISIBLE

        })

        return binding.root
    }

}


