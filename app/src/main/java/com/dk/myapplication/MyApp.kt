package com.dk.myapplication

import android.app.Application
import android.content.Context

//컨텍스트를 사용하기 편하게 하기 위한 세팅
class MyApp : Application() {

    init{
         instance = this
    }

    companion object{

        private var instance : MyApp? = null

        fun context() : Context{
            return instance!!.applicationContext
        }
    }
}