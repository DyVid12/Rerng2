package com.example.rerngapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rerngapp.model.ApiManager
import com.example.rerngapp.model.ApiState
import com.example.rerngapp.model.Result
import com.example.rerngapp.model.State
import kotlinx.coroutines.launch

class DataNowplayViewModel: ViewModel() {

    private val _dataState = MutableLiveData<ApiState<List<Result>>>()
    val dataState: LiveData<ApiState<List<Result>>> get() = _dataState
    fun loadNowplaying(page: Int) {  // Pass page as a parameter
        val dataService = ApiManager.getDataService()
        viewModelScope.launch {
            try {
                val dataResponse = dataService.getNowPlayingMovies(page) // No need for execute()
                _dataState.postValue(ApiState(State.SUCCESS, dataResponse.results))
            } catch (ex: Exception) {
                Log.e("ruppite", "Error while loading data: $ex")
                _dataState.postValue(ApiState(State.ERROR, null))
            }
        }
    }



}