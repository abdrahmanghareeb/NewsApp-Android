package com.route.newsapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//          ***@Get the target address , the Query , call Response
interface NewsService {

    @GET("top-headlines/sources")
    fun getNewsSourses(@Query("apiKey") apiKey : String) : Call<Response>
}