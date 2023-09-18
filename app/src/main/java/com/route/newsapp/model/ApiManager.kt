package com.route.newsapp.model

import com.google.gson.internal.GsonBuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//      ***singleton pattern***
//      ***send the base address , connect it with the api key
class ApiManager private constructor(){
    companion object{

        private var Instance : Retrofit? = null

        private fun getInstance() : Retrofit{
            if(Instance == null){
                Instance = Retrofit
                    .Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return Instance!!
        }
        fun getNewsService(): NewsService {
        return getInstance().create(NewsService::class.java)
        }
    }
}