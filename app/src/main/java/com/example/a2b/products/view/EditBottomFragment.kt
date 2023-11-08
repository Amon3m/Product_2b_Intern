package com.example.a2b.products.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.a2b.R
import com.example.a2b.database.ConcreteLocalSource
import com.example.a2b.databinding.FragmentEditBottomBinding
import com.example.a2b.databinding.FragmentProductsBinding
import com.example.a2b.home.viewmodel.HomeViewModel
import com.example.a2b.home.viewmodel.HomeViewModelFactory
import com.example.a2b.model.ProductsModel
import com.example.a2b.model.Repository
import com.example.a2b.products.viewmodel.ProductsViewModel
import com.example.a2b.products.viewmodel.ProductsViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class EditBottomFragment   : BottomSheetDialogFragment() {
    lateinit var productsViewModel: ProductsViewModel
    lateinit var productsViewModelFactory: ProductsViewModelFactory

    lateinit var homeViewModel: HomeViewModel
    lateinit var homeViewModelFactory: HomeViewModelFactory
    lateinit var binding: FragmentEditBottomBinding
    var productId:Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsViewModelFactory = ProductsViewModelFactory(
            Repository.getInstance(
                ConcreteLocalSource(requireContext())
            )
        )
        productsViewModel = ViewModelProvider(this, productsViewModelFactory).get(ProductsViewModel::class.java)
        homeViewModelFactory = HomeViewModelFactory(
            Repository.getInstance(
                ConcreteLocalSource(requireContext())
            )
        )
        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)



        productId = arguments?.getLong("productId", -1) ?:-1


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBottomBinding.inflate(inflater, container, false)
        return binding.root     }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.quantityEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val quantityStr = s?.toString()
                val quantity=quantityStr?.toIntOrNull() ?: 0

                val priceStr = binding.priceEdit.text?.toString()
                val price = priceStr?.toIntOrNull() ?: 0

                val totalPrice= homeViewModel.calcTotalPrice(price,quantity)
                binding.totalPriceTxt.text=totalPrice.toString()


            }

            override fun afterTextChanged(p0: Editable?) {
            }


        })

        binding.priceEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val priceStr = s?.toString()
                val price=priceStr?.toIntOrNull() ?: 0
                val quantityStr = binding.quantityEdit.text?.toString()
                val quantity = quantityStr?.toIntOrNull() ?: 0


                Log.e("calc","priceEdit = $price , quantity = $quantity ")
                val totalPrice= homeViewModel.calcTotalPrice(price,quantity)
                binding.totalPriceTxt.text=totalPrice.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
            }


        })



        binding.editButton.setOnClickListener {
            val price = binding.priceEdit.text?.toString() ?: "0"
            val quantity = binding.quantityEdit.text?.toString() ?: "0"
            val name = binding.nameEdit.text?.toString() ?: "0"
            val totalPrice = binding.totalPriceTxt.text?.toString() ?: "0"

            val product = ProductsModel(
                id=productId,
                productName = name,
                productQuantity = quantity.toInt(),
                productPrice = price.toDouble(),
                totalPrice = totalPrice.toDouble()
            )

            productsViewModel.updateProduct(product)
            Toast.makeText(requireContext(), "Product edited", Toast.LENGTH_SHORT).show()
            dismiss()

        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

    }

}