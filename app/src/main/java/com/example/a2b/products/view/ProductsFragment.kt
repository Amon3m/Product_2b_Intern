package com.example.a2b.products.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2b.R
import com.example.a2b.database.ConcreteLocalSource
import com.example.a2b.databinding.FragmentHomeBinding
import com.example.a2b.databinding.FragmentProductsBinding
import com.example.a2b.home.viewmodel.HomeViewModel
import com.example.a2b.home.viewmodel.HomeViewModelFactory
import com.example.a2b.model.ProductsModel
import com.example.a2b.model.Repository
import com.example.a2b.products.viewmodel.ProductsViewModel
import com.example.a2b.products.viewmodel.ProductsViewModelFactory
import kotlinx.coroutines.launch


class ProductsFragment : Fragment(),OnProductsClickListener {
    lateinit var productsViewModel: ProductsViewModel
    lateinit var productsViewModelFactory: ProductsViewModelFactory
    lateinit var productsAdapter: ProductsAdapter
    lateinit var binding: FragmentProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsViewModelFactory = ProductsViewModelFactory(
            Repository.getInstance(
                ConcreteLocalSource(requireContext())
            )
        )
        productsViewModel = ViewModelProvider(this, productsViewModelFactory).get(ProductsViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsAdapter = ProductsAdapter(requireContext(),this)
        val myLayoutManager = LinearLayoutManager(requireContext())
        myLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recProduct.apply {
            adapter = productsAdapter
            layoutManager = myLayoutManager
        }

        lifecycleScope.launch {
            productsViewModel.products.collect {
                Log.e("hello","")

//                Log.e("hello","${it.get(0).productName}")
                productsAdapter.submitList(it)

                }
            }
        }


    override fun onEditeClick(productsModel: ProductsModel) {
        val bottomFragment = EditBottomFragment()
        val bundle = Bundle()
        bundle.putLong("productId", productsModel.id)
        bottomFragment.arguments = bundle
        bottomFragment.show(childFragmentManager, bottomFragment.tag)
    }

    override fun onDeleteClick(productsModel: ProductsModel) {
        productsViewModel.removeFromProducts(productsModel)
        Toast.makeText(requireContext(), "Product deleted", Toast.LENGTH_SHORT).show()

    }

}