package com.example.a2b.model

import com.example.a2b.database.LocalSource
import kotlinx.coroutines.flow.Flow

class Repository private constructor(
    var concreteLocalSource: LocalSource
) : RepoInterface {


    companion object {
        private var instance: Repository? = null
        fun getInstance(
            concreteLocalSource: LocalSource
        ): Repository {
            return instance ?: synchronized(this) {
                val temp = Repository(concreteLocalSource)
                instance = temp
                temp
            }
        }
    }




    override fun getProductsFromDatabase(): Flow<List<ProductsModel>> {
        return concreteLocalSource.getAllProducts()
    }

    override suspend fun addToProducts(product: ProductsModel) {
        concreteLocalSource.insertProduct(product)
    }

    override suspend fun deleteFromProducts(product: ProductsModel) {
        concreteLocalSource.deleteProduct(product)
    }

    override suspend fun editInProducts(product: ProductsModel) {
        concreteLocalSource.updateProduct(product)
    }
}