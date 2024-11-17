package com.example.rerngapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rerngapp.adapter.pupularmoviesadapter
import com.example.rerngapp.databinding.ActivityPupularMovies2Binding
import com.example.rerngapp.model.State
import com.example.rerngapp.viewmodel.DataPupularViewModel

class PopularMoviesActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityPupularMovies2Binding
    private lateinit var popularMoviesAdapter: pupularmoviesadapter
    private val viewModelPopular by viewModels<DataPupularViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pupular_movies2)

        // Set up view binding
        binding = ActivityPupularMovies2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the adapter
        popularMoviesAdapter = pupularmoviesadapter()  // Ensure correct capitalization and constructor call

        // Set up RecyclerView
        binding.recyclepupularactivity.apply {
            layoutManager = GridLayoutManager(this@PopularMoviesActivity2, 3)
            adapter = popularMoviesAdapter
        }

        // Observe data state from the view model
        viewModelPopular.dataState.observe(this) { dataState ->
            when (dataState.state) {
                State.SUCCESS -> popularMoviesAdapter.submitList(dataState.results)
                State.ERROR -> Toast.makeText(this, "Error loading data. Please try again", Toast.LENGTH_LONG).show()
            }
        }

        // Load data
        viewModelPopular.loadPupulardata(page = 1)
    }
}
