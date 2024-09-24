package com.dk.myapplication.repository

import com.dk.myapplication.MyApp
import com.dk.myapplication.db.MyDB
import com.dk.myapplication.db.entity.NumEntity

//재사용성이 용이하기에 레파지토리 분리
class Repository {
    
    //CRUD
    private val context = MyApp.context()

    private val db = MyDB.getDB(context)

    fun create(numEntity: NumEntity) = db.numDao().create(numEntity)

    fun read() = db.numDao().read()

    fun update(numEntity: NumEntity) = db.numDao().update(numEntity)

    fun delete(numEntity: NumEntity) = db.numDao().delete(numEntity)


}