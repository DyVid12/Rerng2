package com.example.rerngapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rerngapp.databinding.ViewHolderDataBinding
import com.example.rerngapp.model.Result
import com.squareup.picasso.Picasso

class datamoviesadapter:ListAdapter<Result, datamoviesadapter.DataViewHolder>(DataDiffCallback()){
    class DataDiffCallback:DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class DataViewHolder(private var binding: ViewHolderDataBinding): RecyclerView.ViewHolder(binding.root){
        fun binDataMovies(result:Result){
            Picasso.get().load("https://image.tmdb.org/t/p/w500${result.poster_path}").into(binding.image1)
            binding.tittlemovies.text ="Text:${result.title}"
            binding.relistdate.text ="Relistdate:${result.release_date}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderDataBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val Result = getItem(position)
        holder.binDataMovies(Result)
    }
}