package com.example.a2b.products.view

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a2b.databinding.ProductItemBinding
import com.example.a2b.model.ProductsModel

class ProductsAdapter (val context: Context, private val listener: OnProductsClickListener)
    : ListAdapter<ProductsModel, ProductsViewHolder>(FavoritesDiffUtil()) {
    lateinit var binding: ProductItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {

        val inflater: LayoutInflater =parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= ProductItemBinding.inflate(inflater,parent,false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentObject =getItem(position)
        holder.binding.nameTxt.text=currentObject.productName
        holder.binding.totalPriceTxt.text=currentObject.totalPrice.toString()
        holder.binding.quantTxt.text=currentObject.productQuantity.toString()
        holder.binding.priceTxt.text=currentObject.productPrice.toString()



        holder.binding.editImg2.setOnClickListener {
            listener.onEditeClick(currentObject)
        }
        holder.binding.deleteImg.setOnClickListener {
            listener.onDeleteClick(currentObject)
        }




    }




}

class ProductsViewHolder(var binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root)




class FavoritesDiffUtil: DiffUtil.ItemCallback<ProductsModel>(){
    override fun areItemsTheSame(oldItem: ProductsModel, newItem: ProductsModel): Boolean {
        return oldItem.productName==newItem.productName
    }

    override fun areContentsTheSame(oldItem: ProductsModel, newItem: ProductsModel): Boolean {
        return oldItem==newItem
    }

}