package com.example.animelookup

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.animelookup.model.Anime
import com.example.animelookup.utils.Testing

class MainViewModel: ViewModel() {
    private var _animes: MutableLiveData<List<Anime>> = MutableLiveData()
    val animeList: MutableLiveData<List<Anime>> get() = _animes
    private var _anime: MutableLiveData<List<Anime>> = MutableLiveData()
    val anime: MutableLiveData<List<Anime>> get() = _anime

    var profilePic: MutableLiveData<String> = MutableLiveData()
    private val repo: Repo = Repo()


    fun setup(context: Context){
        if(profilePic.value != null)
            return
    }

    suspend fun setAdapter(){
        val response = AnimeRequest.animeClient.fetchAnimeList(Repo.getApiHeaders()).body()!!
        _animes.value = response
        Log.d(Resources.TEST.toString(), "setAdapter: $response")
    }

    suspend fun getAnimeInfo(title: String){
        val response = AnimeRequest.animeClient.fetchAnimeInfo(Repo.getApiHeaders(), title).body()
        _anime.value = response
    }

    fun infoFragSetup(url: String): MutableLiveData<Anime>{
        return repo.getAnimeInfo(url)
    }

}