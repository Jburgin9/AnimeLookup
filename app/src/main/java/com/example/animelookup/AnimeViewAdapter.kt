package com.example.animelookup;

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.animelookup.databinding.ActivityMainBinding
import com.example.animelookup.databinding.ItemLayoutBinding
import com.example.animelookup.model.Anime
import com.example.animelookup.utils.Testing
import com.squareup.picasso.Picasso

internal class AnimeViewAdapter(private val animeList: List<Anime>) : RecyclerView.Adapter<AnimeViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.animeTitle.text = animeList.get(position).title
        val imageAddress = animeList.get(position).image.substring(28)
        //Setting circleimageview with api response
        if(!imageAddress.endsWith("svg")){
            Picasso.get().load(imageAddress.toUri()).into(holder.itemBinding.animeImage)
        } else {
            Picasso.get().load("https://www.salonlfc.com/wp-content/uploads/2018/01/image-not-found-1-scaled-1150x647.png".toUri()).into(holder.itemBinding.animeImage)
        }

        //click action sends user to Info frag with anime name being sent through args
        holder.itemBinding.animeTitle.setOnClickListener{
            val bundle = Bundle()
            val animeAddress = animeList.get(position).address
            val watched = animeList.get(position).watched
            bundle.putString("url", animeAddress)
            bundle.putBoolean("watched", watched)
            Navigation.findNavController(holder.itemBinding.root)
                .navigate(R.id.action_homeScreen_to_infoScreen, bundle)
        }

        holder.itemBinding.watched.setOnClickListener{
            val anime = animeList.get(position)
            if(anime.watched){
                anime.watched = !anime.watched
                holder.itemBinding.watched.setImageResource(R.drawable.ic_not_watched)
            } else {
                anime.watched = !anime.watched
                holder.itemBinding.watched.setImageResource(R.drawable.ic_watched)
            }
        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    internal inner class ViewHolder(val itemBinding: ItemLayoutBinding): RecyclerView.ViewHolder(itemBinding.root){

    }
}
