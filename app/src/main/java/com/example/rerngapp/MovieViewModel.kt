import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rerngapp.model.Result
import com.example.rerngapp.service.MovieApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val movieApiService: MovieApiService) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<Result>?>(null)
    val searchResults: StateFlow<List<Result>?> get() = _searchResults

    private val _errorState = MutableStateFlow<String?>(null)  // To handle errors
    val errorState: StateFlow<String?> get() = _errorState

    fun searchMovies(query: String, page: Int) {
        viewModelScope.launch {
            try {
                val response = movieApiService.searchMoviesByTitle(query, page)
                if (response.results.isNullOrEmpty()) {
                    _errorState.value = "No movies found"
                    _searchResults.value = emptyList()
                } else {
                    _searchResults.value = response.results
                }
            } catch (e: Exception) {
                _errorState.value = "An error occurred. Please try again."
                e.printStackTrace()  // Log the error if needed
                _searchResults.value = emptyList()  // Provide an empty list as fallback
            }
        }
    }
}
