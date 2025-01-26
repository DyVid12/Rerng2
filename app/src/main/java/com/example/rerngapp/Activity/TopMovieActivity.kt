package com.example.rerngapp.Activity

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rerngapp.R
import com.squareup.picasso.Picasso

class TopMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_top_movie)

        val posterPath = intent.getStringExtra("poster_path")
        val releasdateTopMovies = intent.getStringExtra("release_date")
        val title = intent.getStringExtra("title")
        val vote_average = intent.getStringExtra("vote_average")
        val vote_count = intent.getStringExtra("vote_count")
        val original_language = intent.getStringExtra("original_language")
        val original_title = intent.getStringExtra("original_title")
        val popularity = intent.getStringExtra("popularity")
        val overview = intent.getStringExtra("overview")

        // Use a sample video URL since the API video field is null
        val videoUrl = "https://youtu.be/hDZ7y8RP5HE?si=x9r-TPm4ABNTXuVW"

        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)

        // Load poster image as background
        val posterUrl = "https://image.tmdb.org/t/p/w500$posterPath"
        Picasso.get().load(posterUrl).into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: android.graphics.Bitmap?, from: Picasso.LoadedFrom?) {
                mainLayout.background = android.graphics.drawable.BitmapDrawable(resources, bitmap)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                mainLayout.background = ContextCompat.getDrawable(this@TopMovieActivity, R.color.main_color) // Set default background in case of failure
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                // Optional: Set a placeholder background
                mainLayout.background = ContextCompat.getDrawable(this@TopMovieActivity, R.color.brown)
            }
        })


        val imageTopMovies = findViewById<ImageView>(R.id.imageTopMovies)
        val titleTopMovies = findViewById<TextView>(R.id.titleTopMovies)
        val releasdateTopMovie = findViewById<TextView>(R.id.releasdateTopMovies)
        val voteaverage = findViewById<TextView>(R.id.vote_average)
        val votecount = findViewById<TextView>(R.id.vote_count)
        val originallanguage = findViewById<TextView>(R.id.original_language)
        val originaltitle = findViewById<TextView>(R.id.original_title)
        val popularitymovies = findViewById<TextView>(R.id.popularity)
        val overviewmovies = findViewById<TextView>(R.id.overview)
        val videoMovie = findViewById<WebView>(R.id.youtubeWebView)

        Picasso.get().load("https://image.tmdb.org/t/p/w500$posterPath").into(imageTopMovies)
        titleTopMovies.text = " $title"
        releasdateTopMovie.text = " $releasdateTopMovies"
        voteaverage.text = "vote average: ${vote_average}"
        votecount.text = "vote count: ${vote_count}"
        originallanguage.text = "Original Language: $original_language"
        originaltitle.text ="Original Title: $original_title"
        popularitymovies.text = "Popularity: $popularity"
        overviewmovies.text = "overview: $overview"

        val videoId = "NOhDyUmT9z0" // Replace with your YouTube video ID
        val youtubeWebView = findViewById<WebView>(R.id.youtubeWebView)
        youtubeWebView.settings.javaScriptEnabled = true
        youtubeWebView.settings.pluginState = WebSettings.PluginState.ON
        youtubeWebView.webViewClient = WebViewClient()
        youtubeWebView.loadUrl(videoUrl)
        }


    }
