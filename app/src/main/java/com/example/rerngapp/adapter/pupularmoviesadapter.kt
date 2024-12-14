package com.example.rerngapp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rerngapp.Activity.TopMovieActivity
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

            binding.pupularimage.setOnClickListener{ v ->

                val context = v.context
                val intent = Intent(context, TopMovieActivity::class.java).apply {

                    putExtra("poster_path", result.poster_path)
                    putExtra("release_date", result.release_date)
                    putExtra("title", result.title)
                    putExtra("vote_average", result.vote_average.toString())
                    putExtra("vote_count", result.vote_count.toString())
                    putExtra("original_language", result.original_language)
                    putExtra("original_title", result.original_title)
                    putExtra("popularity", result.popularity.toString())
                    putExtra("overview", result.overview)
                }
                Log.d("video", "popularity: ${result.vote_average}")
                Log.d("video", "vote_count: ${result.vote_count}")
                context.startActivity(intent)

            }

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