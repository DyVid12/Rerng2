import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {

    // LiveData to observe the popular movies list
    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> get() = _popularMovies

    // Function to fetch popular movies
    fun fetchPopularMovies(apiKey: String) {
        RetrofitInstance.api.getPopularMovies(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    _popularMovies.value = response.body()!!.results
                } else {
                    // Handle error in response
                    _popularMovies.value = emptyList()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Handle failure in request
                _popularMovies.value = emptyList()
            }
        })
    }
}
