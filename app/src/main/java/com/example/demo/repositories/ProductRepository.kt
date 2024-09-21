package com.example.demo.repositories

import com.example.demo.models.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRepository {
    private val URLBASE = "http://10.0.2.2:3000";

    private  val productAPI : ProductAPI

    init {
        val retrofit = Retrofit.Builder().baseUrl(URLBASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        productAPI = retrofit.create(ProductAPI::class.java)

    }

    fun fetchProducts(callback: (List<Product>?) -> Unit) {
        productAPI.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                callback(null)
            }
        })
    }
    fun fetchProductById(id: Int, callback: (Product?) -> Unit) {
        productAPI.getProductById(id).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    val product = response.body()
                    callback(product)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                callback(null)
            }
        })
    }

}