package com.example.animequotesv2.cliente

import com.example.animequotesv2.service.FraseService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FraseCliente {

    companion object{
        //private val url = "https://animechan.vercel.app/api/"

        //DIRECCION DE PRUEBAS
        private var url = "https://127.0.0.1:8080/"

        fun obtenCliente(): FraseService{
            val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()).build()
            return retrofit.create(FraseService::class.java)
        }
    }

}

