package com.example.a2b.database

import android.content.Context
import com.example.a2b.model.ProductsModel
import kotlinx.coroutines.flow.Flow

class ConcreteLocalSource(private val context: Context) : LocalSource  {
    private val productsDao: ProductsDao by lazy {
        val db: ProductsDatabase = ProductsDatabase.getInstance(context)
        db.getProductsDao()
    }

    override suspend fun insertProduct(productsModel: ProductsModel) {
        productsDao.insertProduct(productsModel)
    }

    override suspend fun deleteProduct(productsModel: ProductsModel) {
        productsDao.deleteProduct(productsModel)
    }

    override suspend fun updateProduct(productsModel: ProductsModel) {
        productsDao.updateProduct(productsModel)
    }

    override fun getAllProducts(): Flow<List<ProductsModel>> {
        return productsDao.getAll()
    }
}