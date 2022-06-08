package com.example.animelookup

import com.example.animelookup.model.Anime
import retrofit2.Response
import retrofit2.http.*

interface AnimeInterface {

    @GET("/all")
    suspend fun fetchAnimeList(@HeaderMap headers: Map<String, String>): Response<List<Anime>>

    @GET("/anime/{title}")
    suspend fun fetchAnimeInfo(@HeaderMap headers: Map<String, String>,
                               @Path("title") animeTitle: String): Response<List<Anime>>
}