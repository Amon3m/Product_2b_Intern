package com.example.a2b.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.a2b.model.ProductsModel
import kotlinx.coroutines.flow.Flow
@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(productsModel: ProductsModel)
    @Delete
    suspend fun deleteProduct(productsModel: ProductsModel)
    @Update
    suspend fun updateProduct(productsModel: ProductsModel)
    @Query("SELECT * FROM products")
    fun getAll(): Flow<List<ProductsModel>>
}