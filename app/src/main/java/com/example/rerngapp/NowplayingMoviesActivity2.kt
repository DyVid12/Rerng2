package com.example.rerngapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rerngapp.adapter.NowPlayingAdapter
import com.example.rerngapp.databinding.ActivityNowplayingMovies2Binding
import com.example.rerngapp.model.State
import com.example.rerngapp.viewmodel.DataNowplayViewModel

class NowplayingMoviesActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityNowplayingMovies2Binding
    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    private val viewModelNowplaying by viewModels<DataNowplayViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set up view binding
        binding = ActivityNowplayingMovies2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the adapter
        nowPlayingAdapter = NowPlayingAdapter()

        // Set up RecyclerView
        binding.recyclenowplayingsactivity.apply {
            layoutManager = GridLayoutManager(this@NowplayingMoviesActivity2, 3)
            adapter = nowPlayingAdapter
        }

        // Observe data state from the view model
        viewModelNowplaying.dataState.observe(this) { dataState ->
            when (dataState.state) {
                State.SUCCESS -> nowPlayingAdapter.submitList(dataState.results) // Corrected to use instance `nowPlayingAdapter`
                State.ERROR -> Toast.makeText(this, "Error loading data. Please try again", Toast.LENGTH_LONG).show()
            }
        }

        // Load data
        viewModelNowplaying.loadNowplaying(page = 1)
    }
}
