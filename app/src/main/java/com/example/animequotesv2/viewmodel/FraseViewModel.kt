package com.example.animequotesv2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animequotesv2.cliente.FraseCliente
import com.example.animequotesv2.model.Frase
import com.example.animequotesv2.service.FraseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FraseViewModel : ViewModel() {

    var unaFrase = MutableLiveData<Frase>()
    var frases = MutableLiveData<List<Frase>>()
    private val service = FraseCliente.obtenCliente()

    fun traerUnaFraseRandom () {
        val call = service.obtenerFraseRandom()
        call.enqueue(object: Callback<Frase> {
            override fun onResponse(call: Call<Frase>, response: Response<Frase>) {
                response.body().let {

                    unaFrase.postValue(it)
                }
            }

            override fun onFailure(call: Call<Frase>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun traerDiezFrases() {

        val call = service.obtenerFrasePorDiez()
        call.enqueue(object : Callback<List<Frase>> {
            override fun onResponse(call: Call<List<Frase>>, response: Response<List<Frase>>) {
                response.body().let {

                    frases.postValue(it)
                }

            }

            override fun onFailure(call: Call<List<Frase>>, t: Throwable) {
                call.cancel()
            }
        })

    }

    fun traerFrasesPorAnime(anime: String) {

        val call = service.obtenerFrasePorAnime(anime)
        call.enqueue(object : Callback<List<Frase>> {
            override fun onResponse(call: Call<List<Frase>>, response: Response<List<Frase>>) {
                response.body().let {

                    frases.postValue(it)
                }

            }

            override fun onFailure(call: Call<List<Frase>>, t: Throwable) {
                call.cancel()
            }
        })

    }

    fun traerFrasesPorPersonaje(personaje: String) {

        val call = service.obtenerFrasePorPersonaje(personaje)
        call.enqueue(object : Callback<List<Frase>> {
            override fun onResponse(call: Call<List<Frase>>, response: Response<List<Frase>>) {
                response.body().let {

                    frases.postValue(it)
                }

            }

            override fun onFailure(call: Call<List<Frase>>, t: Throwable) {
                call.cancel()
            }
        })
    }
}