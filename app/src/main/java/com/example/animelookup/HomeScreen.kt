package com.example.animelookup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animelookup.databinding.FragmentHomeScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreen : Fragment() {
    private val viewModel:MainViewModel by navGraphViewModels(R.id.nav_graph)
    private lateinit var adapter: AnimeViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.setAdapter()
            binding.recyclerview.layoutManager = LinearLayoutManager(context)
            adapter = AnimeViewAdapter(viewModel.animeList.value!!)
            binding.recyclerview.adapter = adapter
        }
        return binding.root
    }

}