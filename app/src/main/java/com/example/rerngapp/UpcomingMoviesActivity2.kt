package com.example.rerngapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rerngapp.adapter.UpcomingMoviesAdapter
import com.example.rerngapp.adapter.pupularmoviesadapter
import com.example.rerngapp.databinding.ActivityPupularMovies2Binding
import com.example.rerngapp.databinding.ActivityUpcomingMovies2Binding
import com.example.rerngapp.model.State
import com.example.rerngapp.viewmodel.DataUpcommingViewModel

class UpcomingMoviesActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityUpcomingMovies2Binding
    private lateinit var UpcomingMoviesAdapter: UpcomingMoviesAdapter
    private val viewModelupcoming by viewModels<DataUpcommingViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upcoming_movies2)

        binding = ActivityUpcomingMovies2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the adapter
        UpcomingMoviesAdapter =UpcomingMoviesAdapter()  // Ensure correct capitalization and constructor call

        // Set up RecyclerView
        binding.recycleupcommingsactivity.apply {
            layoutManager = GridLayoutManager(this@UpcomingMoviesActivity2, 3)
            adapter = UpcomingMoviesAdapter
        }

        // Observe data state from the view model
        viewModelupcoming.dataState.observe(this) { dataState ->
            when (dataState.state) {
                State.SUCCESS -> UpcomingMoviesAdapter.submitList(dataState.results)
                State.ERROR -> Toast.makeText(this, "Error loading data. Please try again", Toast.LENGTH_LONG).show()
            }
        }

        // Load data
        viewModelupcoming.loadUpcommingdata(page = 1)



    }
}