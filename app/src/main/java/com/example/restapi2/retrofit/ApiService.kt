package com.example.restapi2.retrofit

import com.example.restapi2.models.MyPostTodoResponse
import com.example.restapi2.models.MyPostTodoReuest
import com.example.restapi2.models.MyTodo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("plan")
    suspend fun getAllTodo():List<MyTodo>

    @POST("plan/")
    suspend fun addTodo(@Body myPostTodoReuest: MyPostTodoReuest) : MyPostTodoResponse
}