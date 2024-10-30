data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String
)

data class MovieResponse(
    val results: List<Movie>
)
