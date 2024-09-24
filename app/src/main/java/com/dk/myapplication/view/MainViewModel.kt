package com.dk.myapplication.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dk.myapplication.db.entity.NumEntity
import com.dk.myapplication.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = Repository()

    lateinit var numEntityList : LiveData<List<NumEntity>>

    fun create(numEntity: NumEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.create(numEntity)

    }

    fun read(){
        numEntityList = repository.read().asLiveData()

    }

    fun update(numEntity: NumEntity) = viewModelScope.launch(Dispatchers.IO){

        val ranNum = (10..99).random().toString()
        numEntity.randomNum = "updated Num : $ranNum"

        repository.update(numEntity)

    }

    fun delete(numEntity: NumEntity) = viewModelScope.launch(Dispatchers.IO){

        repository.delete(numEntity)
    }

}