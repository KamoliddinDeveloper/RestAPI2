package com.example.restapi2.repozitore

import com.example.restapi2.models.MyPostTodoReuest
import com.example.restapi2.retrofit.ApiService

class TodoReposirory(val apiService: ApiService) {
    suspend fun getAllTodo() = apiService.getAllTodo()
    suspend fun addTodo(myPostTodoReuest: MyPostTodoReuest) = apiService.addTodo(myPostTodoReuest)
}