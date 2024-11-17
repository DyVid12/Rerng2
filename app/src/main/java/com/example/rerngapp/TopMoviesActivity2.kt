package com.example.rerngapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rerngapp.adapter.datamoviesadapter
import com.example.rerngapp.databinding.ActivityTopMovies2Binding
import com.example.rerngapp.model.State
import com.example.rerngapp.viewmodel.Dataviewmodel


class TopMoviesActivity2 : AppCompatActivity() {

    private lateinit var binding:ActivityTopMovies2Binding
    private lateinit var datamoviesadapter: datamoviesadapter
    private val viewModel by viewModels<Dataviewmodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_top_movies2)

        binding = ActivityTopMovies2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the adapter
        datamoviesadapter = datamoviesadapter()

        // Set up RecyclerView
        binding.recycleTopmoviesactivity.apply {
            layoutManager = GridLayoutManager(this@TopMoviesActivity2, 3)
            adapter = datamoviesadapter
        }

        // Observe data state from the view model
        viewModel.dataState.observe(this) { dataState ->
            when (dataState.state) {
                State.SUCCESS -> datamoviesadapter.submitList(dataState.results)
                State.ERROR -> Toast.makeText(this, "Error loading data. Please try again", Toast.LENGTH_LONG).show()
            }
        }

        // Load data
        viewModel.loadData(page = 1)
    }
}