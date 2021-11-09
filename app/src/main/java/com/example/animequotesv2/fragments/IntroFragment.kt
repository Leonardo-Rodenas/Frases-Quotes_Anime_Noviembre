package com.example.animequotesv2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.animequotesv2.R
import com.example.animequotesv2.adapter.FraseAdapter
import com.example.animequotesv2.cliente.FraseCliente
import com.example.animequotesv2.databinding.FragmentIntroBinding
import com.example.animequotesv2.model.Frase
import com.example.animequotesv2.viewmodel.FraseViewModel
import java.lang.Exception

class IntroFragment : Fragment() {

    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmodel: FraseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIntroBinding.inflate(inflater, container, false)

        binding.progressBarr.visibility = View.VISIBLE
        binding.cardviewIntroFrase.visibility = View.GONE

        vmodel = ViewModelProvider(this).get(FraseViewModel::class.java)

        vmodel.traerUnaFraseRandom()

        vmodel.unaFrase.observe(viewLifecycleOwner, Observer {

            try {

                binding.tvAnime.text = it.anime
                binding.tvPersonaje.text = it.character
                binding.tvFrase.text = it.quote


            } catch (ex: Exception) {
                Log.e("ErrorUnaFraseRandom", ex.message.toString())
                Toast.makeText(context, "Frase inicial mal cargada", Toast.LENGTH_SHORT).show()
            }

            binding.progressBarr.visibility = View.GONE
            binding.cardviewIntroFrase.visibility = View.VISIBLE
        })

        binding.btnRandom.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_fraseRandomFragment)
        }

        binding.btnAnime.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_diezFrasesAnimeFragment)
        }

        binding.btnPersonaje.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_diezFrasesPersonajeFragment)
        }

        return binding.root
    }

}