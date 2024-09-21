package com.example.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.models.Product
import com.example.demo.repositories.ProductRepository

class ProductViewModel : ViewModel() {
    private val _selectedProduct = MutableLiveData<Product?>()
    val selectedProduct: LiveData<Product?> get() = _selectedProduct
    private val productRepository : ProductRepository = ProductRepository()

    fun getProductById(id: Int) {
        productRepository.fetchProductById(id) {
            product: Product? ->  _selectedProduct.value = product
        }
    }
}