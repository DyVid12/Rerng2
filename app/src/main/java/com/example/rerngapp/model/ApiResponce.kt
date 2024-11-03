package com.example.rerngapp.model

class ApiResponse<T>(
    val page: Int,
    val results: T? = null
)
