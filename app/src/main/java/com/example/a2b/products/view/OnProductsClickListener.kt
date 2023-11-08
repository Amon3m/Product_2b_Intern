package com.example.a2b.products.view

import com.example.a2b.model.ProductsModel

interface OnProductsClickListener {
    fun onEditeClick(productsModel: ProductsModel)
    fun onDeleteClick(productsModel: ProductsModel)
}
