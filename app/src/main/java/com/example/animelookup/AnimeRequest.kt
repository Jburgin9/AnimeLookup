package com.example.animelookup

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimeRequest {
    private const val BASE_URL = "https://top-anime.p.rapidapi.com"
    val animeClient: AnimeInterface by lazy {
        val retro = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retro.create(AnimeInterface::class.java)
    }




}