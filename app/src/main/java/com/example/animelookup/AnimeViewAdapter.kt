package com.example.animelookup;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.animelookup.databinding.ActivityMainBinding
import com.example.animelookup.databinding.ItemLayoutBinding
import com.example.animelookup.model.Anime
import com.squareup.picasso.Picasso

internal class AnimeViewAdapter(val animeList: List<Anime>) : RecyclerView.Adapter<AnimeViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.animeTitle.text = animeList.get(position).title
        val imageAddress = animeList.get(position).image.substring(28)
        Picasso.get().load(imageAddress.toUri()).into(holder.itemBinding.animeImage)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    internal inner class ViewHolder(val itemBinding: ItemLayoutBinding): RecyclerView.ViewHolder(itemBinding.root){

    }
}
