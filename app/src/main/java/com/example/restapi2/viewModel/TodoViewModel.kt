package com.example.restapi2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapi.utils.Resource
import com.example.restapi2.models.MyPostTodoResponse
import com.example.restapi2.models.MyPostTodoReuest
import com.example.restapi2.models.MyTodo
import com.example.restapi2.repozitore.TodoReposirory
import com.example.restapi2.retrofit.ApiClient
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class TodoViewModel(val todoRepository : TodoReposirory) : ViewModel() {
    private val liveData = MutableLiveData<Resource<List<MyTodo>>>()
    private    val apiService = ApiClient.getApiService()

    fun getAllTodo(): MutableLiveData<Resource<List<MyTodo>>> {


        viewModelScope.launch {
            liveData.postValue(Resource.loading("loading"))

            try {

                coroutineScope {
                    val list = async {
                        todoRepository.getAllTodo()
                    }.await()
                    liveData.postValue(Resource.succsess(list))
                }
            } catch (e: Exception) {
                liveData.postValue(Resource.error(e.message))
            }
        }
        return liveData
    }

    private val postLiveData = MutableLiveData<Resource<MyPostTodoResponse>>()
    fun addMyTodo(myPostTodoRequest : MyPostTodoReuest) : MutableLiveData<Resource<MyPostTodoResponse>> {
        viewModelScope.launch {
            postLiveData.postValue(Resource.loading("Loading post"))
            try {
                coroutineScope {
                    val response = async {
                        todoRepository.addTodo(myPostTodoRequest)
                    }.await()
                    postLiveData.postValue(Resource.succsess(response))
                    getAllTodo()
                }

            }catch (e:Exception){
                postLiveData.postValue(Resource.error(e.message))
            }
        }
        return postLiveData
    }
}