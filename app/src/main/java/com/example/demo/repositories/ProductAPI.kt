package com.example.demo.repositories

import com.example.demo.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {

    @GET("/api/product")
    fun getProducts(): Call<List<Product>>

    @GET("/api/product/{id}")
    fun getProductById(@Path("id") id: Int): Call<Product>
}