package com.example.animelookup

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animelookup.databinding.ActivityMainBinding
import kotlinx.coroutines.*

private lateinit var binding: ActivityMainBinding
private lateinit var adapter: AnimeViewAdapter

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        test()

    }

    fun test(){
        CoroutineScope(Dispatchers.Main).launch {
            val response = AnimeRequest.animeClient.fetchAnimeList(getApiHeaders()).body()!!
            adapter = AnimeViewAdapter(response)
            binding.recyclerview.adapter = adapter
            binding.recyclerview.layoutManager = LinearLayoutManager(applicationContext)
            Log.d("Test", "test: $response")

        }
    }

    fun getApiHeaders(): Map<String, String>{
        var map: HashMap<String, String> = HashMap()
        map.put("X-RapidAPI-Host", "top-anime.p.rapidapi.com")
        map.put("X-RapidAPI-Key", "25b765628bmsheab7d9b25f691f6p1dc81djsnefd51f015226")
        return map

    }


}