package com.route.newsapp.API_model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//      ***singleton pattern***
//      ***send the base address , connect it with the api key
class ApiManager private constructor(){
    companion object{
        //create instance from Retrofit
        private var Instance : Retrofit? = null
        //give values for the Instance object
        private fun getInstance() : Retrofit{
            if(Instance == null){
                Instance = Retrofit
                    .Builder()
        //      ***send the base address , connect it with the api key
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return Instance!!
        }

//          ***@Get the target address , the Query , call Response
        fun getNewsService(): NewsService {
        return getInstance().create(NewsService::class.java)
        }
    }
}