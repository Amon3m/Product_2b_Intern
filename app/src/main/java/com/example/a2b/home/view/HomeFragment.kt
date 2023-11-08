package com.example.a2b.home.view

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
import androidx.navigation.fragment.findNavController
import com.example.a2b.R
import com.example.a2b.database.ConcreteLocalSource
import com.example.a2b.databinding.FragmentHomeBinding
import com.example.a2b.databinding.FragmentLoginBinding
import com.example.a2b.home.viewmodel.HomeViewModel
import com.example.a2b.home.viewmodel.HomeViewModelFactory
import com.example.a2b.model.ProductsModel
import com.example.a2b.model.Repository


class HomeFragment : Fragment() {
    lateinit var homeViewModel: HomeViewModel
    lateinit var homeViewModelFactory: HomeViewModelFactory
    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModelFactory = HomeViewModelFactory(
            Repository.getInstance(
                ConcreteLocalSource(requireContext())
            )
        )
        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

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

        binding.addButton.setOnClickListener {
            val price = binding.priceEdit.text?.toString() ?: "0"
            val quantity = binding.quantityEdit.text?.toString() ?: "0"
            val name = binding.nameEdit.text?.toString() ?: "0"
            val totalPrice = binding.totalPriceTxt.text?.toString() ?: "0"

            // Create a new ProductsModel instance without specifying the 'id'
            val product = ProductsModel(
                productName = name,
                productQuantity = quantity.toInt(),
                productPrice = price.toDouble(),
                totalPrice = totalPrice.toDouble()
            )

            homeViewModel.addToFavorites(product)
            Toast.makeText(requireContext(), "Product added", Toast.LENGTH_SHORT).show()
        }

        binding.viewButton.setOnClickListener {
            findNavController().navigate(R.id.productsFragment)
        }

    }


}