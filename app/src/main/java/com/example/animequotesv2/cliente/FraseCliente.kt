package com.example.animequotesv2.cliente

import com.example.animequotesv2.service.FraseService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FraseCliente {

    companion object{
        private val url = "https://animechan.vercel.app/api/"

        //uso de esta constante cliente?
        //private val cliente = FraseCliente

        fun obtenCliente(): FraseService{
            val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()).build()
            return retrofit.create(FraseService::class.java)
        }
    }

}