package com.example.a2b.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2b.model.ProductsModel
import com.example.a2b.model.RepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repoInterface: RepoInterface) : ViewModel()  {

    fun calcTotalPrice(price:Int,quantity:Int):Int{
        return (price*quantity)

    }

    fun addToFavorites(productsModel: ProductsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repoInterface.addToProducts(productsModel)
        }
    }
}