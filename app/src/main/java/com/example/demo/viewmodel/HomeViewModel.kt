package com.example.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.models.Product
import com.example.demo.repositories.ProductRepository

class HomeViewModel : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    private val productRepository : ProductRepository = ProductRepository()
     fun loadProducts() {
        productRepository.fetchProducts{ product ->
            _productList.postValue(product)
        }
    }


}
