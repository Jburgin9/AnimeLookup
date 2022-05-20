package com.example.animelookup

import com.example.animelookup.model.Anime
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface AnimeInterface {

    @GET("/all")
    suspend fun fetchAnimeList(@HeaderMap headers: Map<String, String>): Response<List<Anime>>
}