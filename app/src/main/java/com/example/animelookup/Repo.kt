package com.example.animelookup

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.animelookup.model.Anime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class Repo {
    companion object {
        var instance: Repo? = null
        lateinit var _context: Context
    }

    fun getInstance(context: Context):Repo {
        _context = context
        if(instance == null)
            instance = Repo()
        return instance!!
    }

    fun getAnimeInfo(address: String): MutableLiveData<Anime> {
        val profilePic = MutableLiveData<Anime>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val doc = Jsoup.connect(address).get()
                val image = doc.select(".pure-1.md-2-3").select("img")
                    .eq(0).attr("src")
                val name = doc.select(".pure-1.md-2-3").select("img")
                    .eq(0).attr("alt")
                val rating = doc.select(".pure-1.md-1-5")
                    .eq(3).select(".avgRating").attr("title")
                val _description = doc.select(".pure-1.md-3-5")
                    .select("p").eq(0).toString()
                val description = _description.subSequence(3, _description.length - 10)
                val anime = Anime(image = image, title = name, address = address, rating = rating,
                    description = description.toString())
                profilePic.postValue(anime)
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
        return profilePic
    }
}