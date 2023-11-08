package com.example.a2b.model

import kotlinx.coroutines.flow.Flow

interface RepoInterface {
    fun getProductsFromDatabase(): Flow<List<ProductsModel>>
    suspend fun addToProducts(product: ProductsModel)
    suspend fun deleteFromProducts(product: ProductsModel)
    suspend fun editInProducts(product: ProductsModel)

}