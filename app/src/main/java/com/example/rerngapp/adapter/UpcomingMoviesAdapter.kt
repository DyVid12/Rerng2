package com.example.rerngapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rerngapp.databinding.ViewHolderUpcommingDataBinding
import com.example.rerngapp.model.Result
import com.squareup.picasso.Picasso

class UpcomingMoviesAdapter : ListAdapter<Result,UpcomingMoviesAdapter.DataUpcomingViewHolder>(DataUpcomingDiffCallback()) {

    class DataUpcomingViewHolder(private val binding: ViewHolderUpcommingDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindDataMovies(result: Result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${result.poster_path}").into(binding.upcommingimage)
            binding.upcommingtitle.text ="${result.title}"
            binding.upcommingrelistdate.text ="${result.release_date}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataUpcomingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderUpcommingDataBinding.inflate(inflater, parent, false)
        return DataUpcomingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataUpcomingViewHolder, position: Int) {
        val result = getItem(position)
        holder.bindDataMovies(result)
    }
}

class DataUpcomingDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

}
