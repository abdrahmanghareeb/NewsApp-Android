package com.route.newsapp.API_model

import com.route.newsapp.widgets.category_SideRoute
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//          ***@Get the target address , the Query , call Response
interface NewsService {
    @GET("top-headlines/sources")
    fun getNewsSources(@Query("apiKey") apiKey : String, @Query("$category_SideRoute") category: String) : Call<Response>
    @GET("everything")
    fun getNewsBySource(@Query("apiKey") apiKey : String, @Query("sources") sources : String) : Call<NewsResponse>
}