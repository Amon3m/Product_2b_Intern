package com.example.a2b.products.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a2b.home.viewmodel.HomeViewModel
import com.example.a2b.model.RepoInterface

class ProductsViewModelFactory(private val _repo: RepoInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass::class.java.isInstance(ProductsViewModel::class.java)) {
            ProductsViewModel(_repo) as T
        } else {
            throw IllegalArgumentException("View Model class not found")
        }
    }
}