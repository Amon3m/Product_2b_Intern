package com.example.a2b.products.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2b.model.ProductsModel
import com.example.a2b.model.RepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel (private val repoInterface: RepoInterface) : ViewModel()  {
    private val _products = MutableStateFlow<List<ProductsModel>>(listOf())
    val products: StateFlow<List<ProductsModel>>
        get() = _products

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repoInterface.getProductsFromDatabase().collect {
                _products.emit(it)
            }
        }
    }
    init {
        getProducts()    }
    fun removeFromProducts(productsModel: ProductsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repoInterface.deleteFromProducts(productsModel)
        }
    }

    fun updateProduct(productsModel: ProductsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repoInterface.editInProducts(productsModel)
        }
    }

}