package com.example.rerngapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rerngapp.adapter.NowPlayingAdapter
import com.example.rerngapp.adapter.UpcomingMoviesAdapter
import com.example.rerngapp.adapter.datamoviesadapter
import com.example.rerngapp.adapter.pupularmoviesadapter
import com.example.rerngapp.databinding.FragmentExploreFragmentBinding
import com.example.rerngapp.databinding.ViewHolderDataBinding
import com.example.rerngapp.model.State
import com.example.rerngapp.viewmodel.DataNowplayViewModel
import com.example.rerngapp.viewmodel.DataPupularViewModel
import com.example.rerngapp.viewmodel.DataUpcommingViewModel
import com.example.rerngapp.viewmodel.Dataviewmodel


class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreFragmentBinding
    private lateinit var datamoviesadapter: datamoviesadapter
    private lateinit var pupularmoviesadapter: pupularmoviesadapter
    private lateinit var UpcomingMoviesAdapter: UpcomingMoviesAdapter
    private lateinit var NowplayingAdapter : NowPlayingAdapter
    private val viewModel by viewModels<Dataviewmodel>()
    private val viewModelPupular by viewModels<DataPupularViewModel>()
    private val viewModelUpcomming by viewModels<DataUpcommingViewModel>()
    private val viewModelNowplaying by viewModels<DataNowplayViewModel>()
    private lateinit var topmoives: TextView
    private lateinit var pupularmovie: TextView
    private lateinit var upcomingmovie: TextView
    private lateinit var nowplayingmovie: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExploreFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        datamoviesadapter = datamoviesadapter()
        pupularmoviesadapter = pupularmoviesadapter()
        UpcomingMoviesAdapter = UpcomingMoviesAdapter()
        NowplayingAdapter = NowPlayingAdapter()


        binding.recycleupcommingmovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = UpcomingMoviesAdapter

        }

        binding.recyclepupularmovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = pupularmoviesadapter
        }

        binding.recycleData.apply {
//            layoutManager = GridLayoutManager(context, 3)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = datamoviesadapter
        }
        binding.recyclenowplaying.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            adapter = NowplayingAdapter
        }

        viewModel.dataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState.state) {
                State.SUCCESS -> datamoviesadapter.submitList(dataState.results)
                State.ERROR -> Toast.makeText(
                    context,
                    "Error loading data. Please try again",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        viewModelPupular.dataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState.state) {
                State.SUCCESS -> pupularmoviesadapter.submitList(dataState.results)
                State.ERROR -> Toast.makeText(
                    context,
                    "Error loading data. Please try again",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        viewModelUpcomming.dataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState.state) {
                State.SUCCESS -> UpcomingMoviesAdapter.submitList(dataState.results)
                State.ERROR -> Toast.makeText(
                    context,
                    "Error loading data. Please try again",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
     viewModelNowplaying.dataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState.state) {
                State.SUCCESS -> NowplayingAdapter.submitList(dataState.results)
                State.ERROR -> Toast.makeText(
                    context,
                    "Error loading data. Please try again",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        topmoives = view.findViewById(R.id.topmovies)
        topmoives.setOnClickListener {
            val intentTopMovies = Intent(requireContext(),TopMoviesActivity2::class.java)
            startActivity(intentTopMovies)
        }
        pupularmovie = view.findViewById(R.id.pupularmovies)
        pupularmovie.setOnClickListener {
            val intentPupularMovie = Intent(requireContext(),PopularMoviesActivity2::class.java)
            startActivity(intentPupularMovie)
        }
       upcomingmovie = view.findViewById(R.id.upcomingMovies)
       upcomingmovie.setOnClickListener {
            val intentUpcomingMovies = Intent(requireContext(),UpcomingMoviesActivity2::class.java)
            startActivity(intentUpcomingMovies)
        }
        nowplayingmovie = view.findViewById(R.id.nowplayingmovies)
        nowplayingmovie.setOnClickListener {
            val intentNowplayingMovies = Intent(requireContext(),NowplayingMoviesActivity2::class.java)
            startActivity(intentNowplayingMovies)
        }


        viewModel.loadData(page = 1)
            viewModelPupular.loadPupulardata(page = 1)
            viewModelUpcomming.loadUpcommingdata(page = 1)
        viewModelNowplaying.loadNowplaying(page =1)

        }


    }
