package com.example.animelookup

import android.content.Context
import androidx.lifecycle.*
import com.example.animelookup.model.Anime

class MainViewModel: ViewModel() {
    private lateinit var _animes: MutableLiveData<List<Anime>>
    val animeList: MutableLiveData<List<Anime>> get() = _animes
    private lateinit var _anime: MutableLiveData<List<Anime>>
    val anime: MutableLiveData<List<Anime>> get() = _anime


    var profilePic: MutableLiveData<String> = MutableLiveData()
    private val repo: Repo = Repo()


    init {
        _animes = MutableLiveData()
        _anime = MutableLiveData()
    }

    fun setup(context: Context){
        if(profilePic.value != null)
            return
    }

    suspend fun setAdapter(){
        val response = AnimeRequest.animeClient.fetchAnimeList(getApiHeaders()).body()!!
        _animes.value = response
    }

    suspend fun getAnimeInfo(title: String){
        val response = AnimeRequest.animeClient.fetchAnimeInfo(getApiHeaders(), title).body()
        _anime.value = response
    }

    fun infoFragSetup(url: String): MutableLiveData<Anime>{
        return repo.getAnimeInfo(url)
    }


    fun getApiHeaders(): Map<String, String> {
            var map: HashMap<String, String> = HashMap()
            map.put("X-RapidAPI-Host", "top-anime.p.rapidapi.com")
            map.put("X-RapidAPI-Key", "25b765628bmsheab7d9b25f691f6p1dc81djsnefd51f015226")
            return map
    }

}