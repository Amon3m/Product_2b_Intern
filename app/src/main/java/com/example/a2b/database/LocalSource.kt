package com.example.a2b.database

import com.example.a2b.model.ProductsModel
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    suspend fun insertProduct(productsModel: ProductsModel)
    suspend fun deleteProduct(productsModel: ProductsModel)
    suspend fun updateProduct(productsModel: ProductsModel)
    fun getAllProducts(): Flow<List<ProductsModel>>
}