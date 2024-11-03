package com.example.rerngapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rerngapp.adapter.datamoviesadapter
import com.example.rerngapp.databinding.FragmentExploreFragmentBinding
import com.example.rerngapp.databinding.ViewHolderDataBinding
import com.example.rerngapp.model.State
import com.example.rerngapp.viewmodel.Dataviewmodel


class ExploreFragment : Fragment() {
    private lateinit var binding:FragmentExploreFragmentBinding
    private lateinit var datamoviesadapter:datamoviesadapter
    private val viewModel by viewModels<Dataviewmodel>()

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

        binding.recycleData.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = datamoviesadapter
        }

        viewModel.dataState.observe(viewLifecycleOwner){ dataState ->
            when(dataState.state){
                State.SUCCESS -> datamoviesadapter.submitList(dataState.results)
                State.ERROR -> Toast.makeText(context, "Error loading data. Please try again", Toast.LENGTH_LONG).show()
            }

        }
        viewModel.loadData(page = 1)
    }


}