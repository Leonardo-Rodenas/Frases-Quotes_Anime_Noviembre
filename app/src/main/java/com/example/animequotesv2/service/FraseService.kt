package com.example.animequotesv2.service


import com.example.animequotesv2.model.Frase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FraseService {

    @GET("random")
    fun obtenerFraseRandom(): Call<Frase>


    @GET("quotes")
    fun obtenerFrasePorDiez(): Call<List<Frase>>


    @GET("quotes/character")
    fun obtenerFrasePorPersonaje(@Query("name", encoded = true) frasePersonaje: String): Call<List<Frase>>


   /* @GET("quotes/character")
    fun obtenerFrasePorPersonaje(@Query("name", encoded = true), @Query("age") frasePersonaje: String): Call<List<Frase>>*/

    @GET("quotes/anime")
    fun obtenerFrasePorAnime(@Query("title", encoded = true) fraseAnime: String): Call<List<Frase>>

}