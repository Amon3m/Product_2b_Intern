package com.example.a2b.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductsModel
    (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val id: Long=0,
    @ColumnInfo val productName :String,
    @ColumnInfo val productQuantity:Int,
    @ColumnInfo val productPrice: Double,
    @ColumnInfo val totalPrice:Double
)
