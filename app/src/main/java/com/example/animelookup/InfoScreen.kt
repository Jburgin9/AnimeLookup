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
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InfoScreen : Fragment() {
    private val viewModel: MainViewModel by navGraphViewModels(R.id.nav_graph)
    private val args: HomeScreenArgs by navArgs()
    private lateinit var profileUrl: MutableLiveData<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LayoutInfoTopPortionBinding.inflate(inflater, container, false)
        viewModel.setup(binding.root.context)
        viewModel.infoFragSetup(args.animeName!!).observe(viewLifecycleOwner, Observer {
            Log.d("Test", "onCreateView: ${it}")
            Picasso.get().load(it.image.toUri()).into(binding.animeProfile)
            binding.animeName.text = it.title
        })
        return binding.root
    }


    private suspend fun getAnime(){
        Log.d("Test", "onCreateView: ${viewModel.getAnimeInfo(args.animeName!!)}")
        Log.d("Test", "onCreateView: ${viewModel.anime.value}")

    }

}