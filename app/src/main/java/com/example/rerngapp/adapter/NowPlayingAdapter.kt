package com.example.rerngapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rerngapp.Activity.TopMovieActivity
import com.example.rerngapp.databinding.ViewHolderNowplayingDataBinding
import com.example.rerngapp.model.Result
import com.squareup.picasso.Picasso

class NowPlayingAdapter : ListAdapter<Result, NowPlayingAdapter.DataNowPlayingViewHolder>(DataNowPlayingDiffCallback()) {

    class DataNowPlayingViewHolder(private val binding: ViewHolderNowplayingDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindDataMovies(result: Result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${result.poster_path}").into(binding.nowplayingimage)
            binding.nowplayingtitle.text = result.title
            binding.nowplayingrrelistdate.text = result.release_date

            binding.nowplayingimage.setOnClickListener{ v ->

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
                context.startActivity(intent)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataNowPlayingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderNowplayingDataBinding.inflate(inflater, parent, false)
        return DataNowPlayingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataNowPlayingViewHolder, position: Int) {
        val result = getItem(position)
        holder.bindDataMovies(result)
    }
}

class DataNowPlayingDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}
