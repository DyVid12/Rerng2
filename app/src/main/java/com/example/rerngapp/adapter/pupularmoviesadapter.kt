package com.example.rerngapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rerngapp.adapter.datamoviesadapter.DataViewHolder
import com.example.rerngapp.databinding.ViewHolderDataBinding
import com.example.rerngapp.databinding.ViewHolderPupularDataBinding
import com.example.rerngapp.model.Result
import com.squareup.picasso.Picasso

class pupularmoviesadapter: ListAdapter<Result,pupularmoviesadapter.DataPupularViewHolder>(DataPupularDiffCallback()) {
    class DataPupularViewHolder(private var binding: ViewHolderPupularDataBinding):RecyclerView.ViewHolder(binding.root) {
        fun binDataMovies(result: Result){
            Picasso.get().load("https://image.tmdb.org/t/p/w500${result.poster_path}").into(binding.pupularimage)
            binding.pupulartitle.text ="${result.title}"
            binding.pupularrelistdate.text ="${result.release_date}"
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataPupularViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderPupularDataBinding.inflate(inflater, parent, false)
        return DataPupularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataPupularViewHolder, position: Int) {
        val Result = getItem(position)
        holder.binDataMovies(Result)
    }
}

class DataPupularDiffCallback: DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
       return oldItem.id == newItem.id
    }

}