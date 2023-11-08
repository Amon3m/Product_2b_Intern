package com.example.a2b.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a2b.model.ProductsModel

@Database(entities = [ProductsModel::class], version = 1)
abstract class ProductsDatabase : RoomDatabase(){
    abstract fun getProductsDao():ProductsDao
    companion object {
        @Volatile
        private var INSTANCE: ProductsDatabase? = null
        fun getInstance(ctx: Context): ProductsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext, ProductsDatabase::class.java, "product_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}