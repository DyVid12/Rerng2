package com.example.rerngapp.model

class ApiState<T> (
    val state: State,
    val results: T? = null
)


enum class State {
                 ERROR, SUCCESS
}