package com.example.rerngapp

import MovieViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rerngapp.adapter.MovieAdapter

import kotlinx.coroutines.flow.collect

class FavouriteFragment : Fragment() {
    // ViewModel instance
//    private val movieViewModel: MovieViewModel by viewModels()
//    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        // Initialize RecyclerView
//        val recyclerView = view.findViewById<RecyclerView>(R.id.recycleData)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        movieAdapter = MovieAdapter()
//        recyclerView.adapter = movieAdapter

        // Initialize SearchView
//        val searchView = view.findViewById<android.widget.SearchView>(R.id.searchView)
//        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                query?.let {
//                    if (it.isNotEmpty()) {
//                        searchMovies(it) // Trigger search
//                    } else {
//                        Toast.makeText(requireContext(), "Please enter a search query", Toast.LENGTH_SHORT).show()
//                    }
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // Optional: Trigger search while typing
//                return true
//            }
//        })

        // Observe ViewModel for search results
//        lifecycleScope.launchWhenStarted {
//            movieViewModel.searchResults.collect { results ->
//                results?.let {
//                    if (it.isNotEmpty()) {
//                        movieAdapter.submitList(it) // Update adapter
//                    } else {
//                        Toast.makeText(requireContext(), "No movies found", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }

        return view
    }

//    private fun searchMovies(query: String) {
//        movieViewModel.searchMovies(query, 1) // Trigger ViewModel to search movies
//    }
}
