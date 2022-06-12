package com.example.animelookup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.example.animelookup.databinding.FragmentInfoScreenBinding
import com.example.animelookup.databinding.LayoutInfoTopPortionBinding
import com.example.animelookup.utils.Testing
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InfoScreen : Fragment() {
    private val viewModel: MainViewModel by navGraphViewModels(R.id.nav_graph)
    private val args: HomeScreenArgs by navArgs()
    private lateinit var binding: FragmentInfoScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoScreenBinding.inflate(inflater, container, false)
        var watched = requireArguments().getBoolean("watched")
        viewModel.setup(binding.root.context)
        viewModel.infoFragSetup(requireArguments().getString("url")!!).observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it.image.toUri()).into(binding.topPortion.animeProfile)
            binding.topPortion.animeName.text = it.title
            binding.animeInfo.text = it.description
            binding.topPortion.animeRating.text = it.rating
            if(watched){
                binding.topPortion.watched.setImageResource(R.drawable.ic_watched)
                watched = !watched
            } else {
                R.drawable.ic_not_watched
                watched = !watched
            }

        })
        binding.topPortion.watched.setOnClickListener{
            binding.topPortion.watched.setImageResource(R.drawable.ic_watched)
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private suspend fun getAnime(){
        Log.d("Test", "onCreateView: ${viewModel.getAnimeInfo(args.animeName!!)}")
        Log.d("Test", "onCreateView: ${viewModel.anime.value}")

    }

}